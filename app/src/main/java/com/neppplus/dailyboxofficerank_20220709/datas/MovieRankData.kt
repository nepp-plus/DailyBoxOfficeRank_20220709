package com.neppplus.dailyboxofficerank_20220709.datas

data class MovieRankData(
    val rnum: String,
    val rank: String,
    val rankInten: String,
    val rankOldAndNew: String,
    val movieCd: String,
    val movieNm: String,
    val openDt: String,
    val salesAmt: String,
    val salesShare: String,
    val salesInten: String,
    val salesChange: String,
    val salesAcc: String,
    val audiCnt: String,
    val audiInten: String,
    val audiChange: String,
    val audiAcc: String,
    val scrnCnt: String,
    val showCnt: String,
) {

//    데이터 클래스도 함수를 갖는게 가능함.

    fun getFormattedRankInten() : String {

//        0 : "-"
//        음수값 : 그대로
//        양수값 : +1, +2 등으로.
//        String인 rank => Int로 바꿔서 사용.

        val rankNum = this.rankInten.toInt()

        if (rankNum == 0) {
            return  "( - )"
        }
        else if (rankNum < 0) {
            return "( ${rankNum} )"
        }
        else {
            return "( +${rankNum} )"
        }


    }


}
