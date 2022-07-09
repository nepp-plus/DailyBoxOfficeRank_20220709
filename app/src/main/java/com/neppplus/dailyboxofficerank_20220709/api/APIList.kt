package com.neppplus.dailyboxofficerank_20220709.api

import com.neppplus.dailyboxofficerank_20220709.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIList {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getRequestDailyBoxOfficeList(
        @Query("key") key: String,
        @Query("targetDt") date: String,
    ) : Call< BasicResponse >

}