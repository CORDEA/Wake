package jp.cordea.wake

import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by Yoshihiro Tanaka on 2017/03/03.
 */
class ProgressTimer(private val total: Int) {

    private var timer: Timer? = null

    private var currentSec: Int = 0

    var onUpdate: (Float) -> Unit = { }

    var startedAt: Date = Date()
        private set

    var endedAt: Date = Date()
        private set

    fun start() {
        startedAt = Date()
        timer = Timer()
        timer?.schedule(0, 1000, {
            currentSec += 1
            if (currentSec > total) {
                currentSec = 0
            }
            onUpdate(currentSec.toFloat() / total.toFloat())
        })
    }

    fun stop() {
        endedAt = Date()
        currentSec = 0
        timer?.cancel()
    }

}