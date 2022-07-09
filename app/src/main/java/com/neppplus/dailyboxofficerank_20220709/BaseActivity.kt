package com.neppplus.dailyboxofficerank_20220709

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neppplus.dailyboxofficerank_20220709.api.APIList
import com.neppplus.dailyboxofficerank_20220709.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

//    API 기능 목록도 상속
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiList = retrofit.create( APIList::class.java )

    }

    abstract fun setupEvents()
    abstract fun setValues()

}