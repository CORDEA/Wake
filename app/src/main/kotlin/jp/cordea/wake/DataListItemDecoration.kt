package jp.cordea.wake

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by cordea on 2017/03/19.
 */
class DataListItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        view?.let {
            parent?.let { parent ->
                val lp = it.layoutParams as RecyclerView.LayoutParams

                if (parent.getChildAdapterPosition(view) == 0) {
                    val margin = context.resources.getDimension(R.dimen.data_card_margin).toInt()
                    lp.topMargin = margin
                } else {
                    lp.topMargin = 0
                }
            }
        }
    }

}