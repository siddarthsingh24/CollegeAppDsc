package com.example.collegeappproject.features.webScrapping


import android.util.Log
import com.example.collegeappproject.models.NoticeModel
import com.example.collegeappproject.utils.Constants
import com.example.collegeappproject.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import javax.inject.Inject


class WebScrappingRepo
@Inject
constructor(private val webScrapingApi: WebScrapingApi)
{

    fun getPost() :Flow<List<NoticeModel>> = flow {
        val list = mutableListOf<NoticeModel>()
        val noticePageDataResult =
            webScrapingApi.getNewsData(Constants.College_WEBSITE_URL_NOTICES)

        if (noticePageDataResult.isSuccessful && noticePageDataResult.body() != null) {
            val htmlDoc = Jsoup.parse(noticePageDataResult.body()!!)
            val link = htmlDoc.getElementsByClass("tab-content").
            select("ul").select("li").select("a")


            link.forEach {
                val element = NoticeModel(it.text(), it.attr("href"))
                Log.d("data", "${element.toString()}  ")
                list.add(element)


            }


        }

        Log.d("meriList", "${list.toString()}  ")

        emit(list)
    }.flowOn(Dispatchers.IO)

    fun getNewPost() :Flow<List<NoticeModel>> = flow {

        val list = mutableListOf<NoticeModel>()
        val noticePageDataResult =
            webScrapingApi.getNewsData(Constants.College_WEBSITE_URL_NEWS)

        if (noticePageDataResult.isSuccessful && noticePageDataResult.body() != null) {
            val htmlDoc = Jsoup.parse(noticePageDataResult.body()!!)
            val link = htmlDoc.getElementsByClass("tab-content").
            select("ul").select("li").select("a")


            link.forEach {
                val element = NoticeModel(it.text(), it.attr("href"))
                Log.d("data", "${element.toString()}  ")
                list.add(element)


            }


        }

        Log.d("meriList", "${list.toString()}  ")

        emit(list)
    }.flowOn(Dispatchers.IO)


}