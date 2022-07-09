package com.neppplus.dailyboxofficerank_20220709.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.dailyboxofficerank_20220709.R
import com.neppplus.dailyboxofficerank_20220709.datas.MovieRankData

class MovieRankAdapter(
    val mContext: Context,
    val mList: ArrayList<MovieRankData>
) : RecyclerView.Adapter<MovieRankAdapter.MyViewHolder>() {

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {

        val txtRank = row.findViewById<TextView>(R.id.txtRank)
        val txtRankChange = row.findViewById<TextView>(R.id.txtRankChange)
        val txtTitle = row.findViewById<TextView>(R.id.txtTitle)
        val txtOpenDate = row.findViewById<TextView>(R.id.txtOpenDate)
        val txtTotalAudi = row.findViewById<TextView>(R.id.txtTotalAudi)

        fun bind( data: MovieRankData ) {

            txtRank.text = data.rank
            txtRankChange.text = data.getFormattedRankInten()
            txtTitle.text = data.movieNm
            txtOpenDate.text = data.openDt
            txtTotalAudi.text = data.getFormattedTotalAudience()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.movie_rank_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind( mList[position] )

    }

    override fun getItemCount() = mList.size

}