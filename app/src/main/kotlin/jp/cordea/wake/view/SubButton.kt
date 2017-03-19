package jp.cordea.wake.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import jp.cordea.wake.R

/**
 * Created by Yoshihiro Tanaka on 2017/03/13.
 */
class SubButton(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val button by lazy {
        findViewById(R.id.button)
    }

    var onClick: () -> Unit = { }

    init {
        inflate(context, R.layout.sub_button, this)

        button.setOnClickListener {
            onClick()
        }
    }
}