# 8小时 用kotlin开发一款app

> 整个项目所花时间:2018年1月1日10:20~2018年1月1日18:27   花了很多时间去爬数据
> 项目地址: https://github.com/xfhy/DeepNight-in-kotlin

下载试玩:
http://fir.im/lga9
![](http://olg7c0d2n.bkt.clouddn.com/18-1-1/40864026.jpg)

### 项目简介

一款纯看妹子的app,素材全部来源于正经图库(如有侵权,请立马告诉本人,本人邮箱xfhy666@qq.com,我定立刻删除),本app仅做学习交流使用,勿做商用.请注意,全是正经图片,可别想歪了哈.

由于时间非常紧急,于是没有用MVP,RxJava2,Retrofit这些都没用.直接用的anko的common库,很方便就可以异步操作和UI线程切换等,为了节约点时间.

直接上图吧,哈哈,图来得直接些(本来是gif的,结果不能上传那么大的):

![](deepnight.gif)

<img src="http://olg7c0d2n.bkt.clouddn.com/18-1-1/23759130.jpg" height=400px/>

<img src="http://olg7c0d2n.bkt.clouddn.com/18-1-1/7102514.jpg" height=400px/>

<img src="http://olg7c0d2n.bkt.clouddn.com/18-1-1/56146306.jpg" height=400px/>

<img src="http://olg7c0d2n.bkt.clouddn.com/18-1-1/38611177.jpg" height=400px/>

<img src="http://olg7c0d2n.bkt.clouddn.com/18-1-1/83801702.jpg" height=400px/>

说实话,项目中有很多需要优化的地方,但是时间确实有点短,来不及了,很多地方命名不规范,因为我每次想命名需要花很多时间,以前我每一个命名都是仔细思考过的.然后很多地方没有注释,也是来不及了.平时我非常注重注释.

### 用到的知识点

- kotlin 语言
    - 数据类
    - 伴生对象
    - 单例
    - 扩展
    - 闭包
    - ...
- anko
- jsoup 爬取网页数据的库
- Material Design 风格
- Recyclerview 瀑布流
- CardView
- Glide 加载图片
- BaseQuickAdapter 以前自己搞的一个adapter,方便快速开发
- DrawerLayout
- NavigationView
- Toolbar
- 下拉刷新,上拉加载
- fragment懒加载

大概就是上面这些了

### 爬取数据

这里我就不过多叙述了(其实是不知道怎么讲...),网站非常规律,看看jsoup用法很容易就搞到手啦,不懂的可以看源码哟.

看代码吧,比我说的更清楚.结合jsoup 开发文档和网页源码一起看哟
```kotlin
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
```

### 构建UI

我是采用的侧滑风格的app,中间的数据全部采用fragment来填充的,fragment只有在显示后才加载数据,不会浪费过多流量.

素材取自阿里矢量图标库

