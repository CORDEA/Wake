package jp.cordea.wake.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import jp.cordea.wake.R
import jp.cordea.wake.model.Lap

/**
 * Created by Yoshihiro Tanaka on 2017/03/09.
 */
class LapListAdapter(context: Context, var items: List<Lap>) : ArrayAdapter<Lap>(context, R.layout.list_item_lap) {

    fun refreshItems(items: List<Lap>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Lap {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val viewHolder: ViewHolder
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_lap, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        viewHolder.title.apply {
            text = context.resources.getString(R.string.session_item_title)
                    .format(item.index, item.lapTime)
        }

        return view!!
    }

    class ViewHolder(view: View) {

        val title = view.findViewById(R.id.title) as TextView

    }
}