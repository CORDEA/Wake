package jp.cordea.wake.viewmodel

import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import jp.cordea.wake.model.Session

/**
 * Created by Yoshihiro Tanaka on 2017/03/18.
 */
class SessionChartListItemViewModel : DataListItemViewModel() {

    val sessionTimeBarData: BarDataSet
        get() {
            val results = realm.where(Session::class.java).findAll()
            val entries = results.map { BarEntry(it.id.toFloat(), getSessionTime(it)) }
            return BarDataSet(entries, "")
        }

}