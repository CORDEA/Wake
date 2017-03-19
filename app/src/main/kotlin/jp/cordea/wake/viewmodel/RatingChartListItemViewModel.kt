package jp.cordea.wake.viewmodel

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import jp.cordea.wake.model.Session

/**
 * Created by Yoshihiro Tanaka on 2017/03/19.
 */
class RatingChartListItemViewModel : DataListItemViewModel() {

    val ratingLineData: LineDataSet
        get() {
            val results = realm.where(Session::class.java).notEqualTo("rating", -1f).findAll()
            return LineDataSet(results.map { Entry(it.id.toFloat(), it.rating) }, "")
        }

}