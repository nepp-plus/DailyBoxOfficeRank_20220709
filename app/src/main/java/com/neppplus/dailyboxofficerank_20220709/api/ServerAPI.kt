package com.neppplus.dailyboxofficerank_20220709.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

    companion object {

        private var retrofit : Retrofit? = null

        private val BASE_URL = "http://www.kobis.or.kr"

        fun getRetrofit() : Retrofit {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build()

            }

            return retrofit!!

        }

    }

}