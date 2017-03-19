package jp.cordea.wake.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import jp.cordea.wake.DataType
import jp.cordea.wake.R
import jp.cordea.wake.viewmodel.*

/**
 * Created by Yoshihiro Tanaka on 2017/03/14.
 */
class DataListAdapter(private val context: Context, private var types: List<DataType> = listOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: List<DataListItemViewModel> by lazy {
        types.map {
            when (it) {
                DataType.AVERAGE_CHART -> AverageChartListItemViewModel()
                DataType.TOTAL_CHART -> TotalChartListItemViewModel()
                DataType.RATING_CHART -> RatingChartListItemViewModel()
                DataType.SESSION_CHART -> SessionChartListItemViewModel()
                DataType.SESSION -> SessionListItemViewModel()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = items[position]
        val type = types[position]
        (holder as? ListViewHolder)?.let {
            if (type == DataType.SESSION) {
                it.title.text = context.resources.getString(R.string.data_list_session_title)
                it.listView.adapter =
                        SessionListAdapter(context, (item as SessionListItemViewModel).sessionData)
                return
            }
        }
        (holder as? LineViewHolder)?.let {
            if (type == DataType.RATING_CHART) {
                it.title.text = context.resources.getString(R.string.data_list_rating_chart_title)
                showLineChart(it.lineChart, (item as RatingChartListItemViewModel).ratingLineData)
                return
            }
        }
        (holder as? BarViewHolder)?.let {
            when (type) {
                DataType.AVERAGE_CHART -> {
                    it.title.text = context.resources.getString(R.string.data_list_average_chart_title)
                    showBarChart(it.barChart, (item as AverageChartListItemViewModel).averageBarData)
                }
                DataType.TOTAL_CHART -> {
                    it.title.text = context.resources.getString(R.string.data_list_total_chart_title)
                    showBarChart(it.barChart, (item as TotalChartListItemViewModel).totalBarData)
                }
                DataType.SESSION_CHART -> {
                    it.title.text = context.resources.getString(R.string.data_list_session_chart_title)
                    showBarChart(it.barChart, (item as SessionChartListItemViewModel).sessionTimeBarData)
                }
            }
        }
    }

    private fun showBarChart(barChart: BarChart, data: BarDataSet) {
        barChart.apply {
            this.data = BarData(data.apply {
                color = ContextCompat.getColor(context, R.color.primary)
            })
            description = Description().apply {
                text = ""
            }
            legend.isEnabled = false
            isDoubleTapToZoomEnabled = false
            setTouchEnabled(false)
            setFitBars(true)
            setPinchZoom(false)
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(false)
            invalidate()
        }
    }

    private fun showLineChart(lineChart: LineChart, data: LineDataSet) {
        lineChart.apply {
            this.data = LineData(data.apply {
                color = ContextCompat.getColor(context, R.color.primary)
                setCircleColor(ContextCompat.getColor(context, R.color.primary))
            })
            description = Description().apply {
                text = ""
            }
            legend.isEnabled = false
            isDoubleTapToZoomEnabled = false
            setTouchEnabled(false)
            setPinchZoom(false)
            setBorderColor(ContextCompat.getColor(context, R.color.primary))
            setGridBackgroundColor(ContextCompat.getColor(context, R.color.primary))
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(false)
            invalidate()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val type = DataType.values()[viewType]
        if (type == DataType.SESSION) {
            return ListViewHolder(inflater.inflate(R.layout.list_item_session_list, parent, false))
        }
        if (type == DataType.RATING_CHART) {
            return LineViewHolder(inflater.inflate(R.layout.list_item_line_chart, parent, false))
        }
        return BarViewHolder(inflater.inflate(R.layout.list_item_bar_chart, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return types[position].ordinal
    }

    class BarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.title) as TextView

        val barChart = view.findViewById(R.id.bar_chart) as BarChart

    }

    class LineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.title) as TextView

        val lineChart = view.findViewById(R.id.line_chart) as LineChart

    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.title) as TextView

        val listView = view.findViewById(R.id.list_view) as ListView

    }

}
