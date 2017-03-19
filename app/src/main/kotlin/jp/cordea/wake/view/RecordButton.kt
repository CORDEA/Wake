package jp.cordea.wake.view

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ProgressBar
import jp.cordea.wake.R

/**
 * Created by Yoshihiro Tanaka on 2017/02/27.
 */
class RecordButton(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val progressBar by lazy {
        findViewById(R.id.progress_bar) as ProgressBar
    }

    private val imageView by lazy {
        findViewById(R.id.image_view) as AppCompatImageView
    }

    private val button by lazy {
        findViewById(R.id.button)
    }

    var progress: Int = 0
        set(value) {
            progressBar.post {
                progressBar.progress = value
            }
            field = value
        }

    var imageResource: Int = 0
        set(value) {
            imageView.setImageResource(value)
            field = value
        }

    var isRunning: Boolean = false
        private set

    var onClick: () -> Unit = { }

    fun stop() {
        isRunning = false
        imageView.setImageResource(if (isRunning) R.drawable.ic_pause_white_48px else R.drawable.ic_play_arrow_white_48px)
    }

    init {
        inflate(context, R.layout.record_button, this)

        button.setOnClickListener {
            onClick()
            isRunning = !isRunning
            imageView.setImageResource(if (isRunning) R.drawable.ic_pause_white_48px else R.drawable.ic_play_arrow_white_48px)
        }
    }
}
