package com.neppplus.dailyboxofficerank_20220709

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.dailyboxofficerank_20220709.databinding.ActivityMainBinding
import com.neppplus.dailyboxofficerank_20220709.datas.BasicResponse
import com.neppplus.dailyboxofficerank_20220709.datas.MovieRankData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mMovieRankList = ArrayList< MovieRankData >()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        API의 요청 (임시 : 무조건 2022/01/01로 요청) => 받은 응답을 순위 목록에 담자.

        apiList.getRequestDailyBoxOfficeList(
            "2a89c2cedd0c7b1f191ea981d791e172",
            "20220101"
        ).enqueue( object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        } )

    }
}