package jp.cordea.wake.viewmodel

import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import jp.cordea.wake.model.Sake
import jp.cordea.wake.model.Session

/**
 * Created by Yoshihiro Tanaka on 2017/03/18.
 */
class TotalChartListItemViewModel : DataListItemViewModel() {

    val totalBarData: BarDataSet
        get() {
            val results = realm.where(Session::class.java).findAll()
            val entries = results.map {
                val sakes = realm.where(Sake::class.java).equalTo("sessionId", it.id).findAll()
                BarEntry(it.id.toFloat(), sakes.map { getVolume(it) }.sum())
            }
            return BarDataSet(entries, "")
        }

}