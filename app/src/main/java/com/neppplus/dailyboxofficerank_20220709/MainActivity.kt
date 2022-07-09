package com.neppplus.dailyboxofficerank_20220709

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.dailyboxofficerank_20220709.adapters.MovieRankAdapter
import com.neppplus.dailyboxofficerank_20220709.databinding.ActivityMainBinding
import com.neppplus.dailyboxofficerank_20220709.datas.BasicResponse
import com.neppplus.dailyboxofficerank_20220709.datas.MovieRankData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mMovieRankList = ArrayList< MovieRankData >()

    lateinit var mAdapter: MovieRankAdapter


    val selectDateFormat = SimpleDateFormat("yyyy년 M월 d일")
    val serverFormat = SimpleDateFormat("yyyyMMdd")


//    순위를 확인하기 위해 선택된 날짜를 저장할 Calendar 변수
    val selectedDateCal = Calendar.getInstance()  // 기본값 : 현재 일시

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.txtDate.setOnClickListener {

//            날짜를 클릭하면 => 날짜 선택 팝업 출현

            val dsl = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    날짜를 선택하면 할 일 적는 공간

//                    선택된 날짜를 => Calendar변수에 반영 (set)
                    selectedDateCal.set(year, month, dayOfMonth)

//                    화면 새로고침 => 확인 일자 문구 변경 / API 서버에 다시 요청
                    refreshData()

                }

            }

            val datePicker = DatePickerDialog(
                mContext,
                dsl,
                selectedDateCal.get(Calendar.YEAR),
                selectedDateCal.get(Calendar.MONTH),
                selectedDateCal.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()


        }

    }

    override fun setValues() {

//        순위 확인 일자를 => 오늘 날짜로 설정. => 기본값 활용 (SimpleDateFormat 연계)


        binding.txtDate.text = selectDateFormat.format( selectedDateCal.time )


        mAdapter = MovieRankAdapter(mContext, mMovieRankList)
        binding.movieRankRecyclerView.adapter = mAdapter
        binding.movieRankRecyclerView.layoutManager = LinearLayoutManager(mContext)


//        API의 요청 (임시 : 무조건 2022/01/01로 요청) => 받은 응답을 순위 목록에 담자.

        apiList.getRequestDailyBoxOfficeList(
            "2a89c2cedd0c7b1f191ea981d791e172",
            "20220101"
        ).enqueue( object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

//                     목록에 등수 데이터 추가
                    mMovieRankList.addAll( br.boxOfficeResult.dailyBoxOfficeList )

                    mAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        } )

    }

    fun refreshData() {
//        선택된 날짜 다시 반영

        binding.txtDate.text = selectDateFormat.format( selectedDateCal.time )

//        선택 된 날짜의 박스오피스 랭킹 정보 재 반영

        apiList.getRequestDailyBoxOfficeList(
            "2a89c2cedd0c7b1f191ea981d791e172",
            serverFormat.format( selectedDateCal.time )
        ).enqueue( object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {
                    val br = response.body()!!

                    mMovieRankList.clear()

                    mMovieRankList.addAll( br.boxOfficeResult.dailyBoxOfficeList )

                    mAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })


    }

}