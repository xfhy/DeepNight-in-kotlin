package com.xfhy.deepnight.net

import com.xfhy.deeplibrary.common.LogUtil
import com.xfhy.deepnight.bean.DivListImgBean
import org.jetbrains.anko.collections.forEachReversedByIndex
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * @author xfhy
 * create at 2018/1/1 12:36
 * description：
 */
object NetUtils {

    /**
     * 获取外部的列表页的list
     * @param url url
     * @param index 索引  从1开始
     * @param response 回调数据
     */
    fun reqOutList(url: String, index: Int, response: ZResponse) {
        doAsync {
            try {
                //组装url
                var buildUrl = "${url}index${if (index == 1) "" else "_$index"}.shtml"

                LogUtil.e("buildUrl = $buildUrl")

                //连接
                val document: Document = Jsoup.connect(buildUrl).get()

                //筛选首页全部的链接   暂时是第一页的
                val htmls: Elements = document.select("a[href$=.shtml]")

                //筛选是可以点击进去详情的url
                htmls.forEachReversedByIndex {
                    val realUrl = it.getElementsByClass("div-img")
                    //不正确的全部移除
                    if (realUrl.size == 0) {
                        htmls.remove(it)
                    }
                }

                //外层列表的bean集合
                val outLists: ArrayList<DivListImgBean> = ArrayList()
                htmls.forEach {

                    val divListImgBean = DivListImgBean("", "","")

                    val linkElements = it.getElementsByClass("link")
                    divListImgBean.detailsUrl = linkElements.attr("href")
                    //获取图片地址
                    val imgSrcs = it.select("img[src\$=.jpg]")
                    imgSrcs.forEach {
                        val src = it.attr("src")
                        val alt = it.attr("alt")
                        divListImgBean.src = src
                        divListImgBean.text = alt
                    }
                    //添加bean到集合中
                    outLists.add(divListImgBean)
                }
                LogUtil.e(outLists.toString())
                uiThread {
                    response.onSuccess(outLists)
                }
            } catch (e: Exception) {
                uiThread {
                    response.onError(e.message)
                }
            }
        }
    }

    /**
     * 详情列表
     */
    fun reqDetails(url: String, response: ZResponse) {
        doAsync {
            try {
                //连接
                val document: Document = Jsoup.connect(url).ignoreContentType(true).get()
                val girlPictureList = document.select("div.div-num")
                val imageList: ArrayList<String> = ArrayList()
                girlPictureList.forEach {
                    if (it.hasAttr("data-src")) {
                        var imgUrl = it.attr("data-src")
                        imgUrl = imgUrl.substring(0, imgUrl.indexOf("?"))
                        imageList.add(imgUrl)
                    }
                }
                LogUtil.e(imageList.toString())
                uiThread {
                    response.onSuccess(imageList)
                }
            } catch (e: Exception) {
                uiThread {
                    response.onError(e.message)
                }

            }
        }
    }

}