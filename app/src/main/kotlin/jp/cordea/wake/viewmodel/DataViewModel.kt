package jp.cordea.wake.viewmodel

import android.content.Context
import jp.cordea.wake.adapter.DataListAdapter
import jp.cordea.wake.DataType

/**
 * Created by Yoshihiro Tanaka on 2017/03/14.
 */
class DataViewModel(context: Context) {

    private val types by lazy {
        listOf(
                DataType.AVERAGE_CHART,
                DataType.SESSION_CHART,
                DataType.TOTAL_CHART,
                DataType.RATING_CHART,
                DataType.SESSION
        )
    }

    val adapter = DataListAdapter(context, types)

}