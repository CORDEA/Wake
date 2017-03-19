package jp.cordea.wake.viewmodel

import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import io.realm.Realm
import jp.cordea.wake.ProgressTimer
import jp.cordea.wake.adapter.LapListAdapter
import jp.cordea.wake.model.Lap
import jp.cordea.wake.model.Preferences
import jp.cordea.wake.model.Sake
import jp.cordea.wake.model.Session
import java.util.*

/**
 * Created by Yoshihiro Tanaka on 2017/03/09.
 */
class RecordViewModel(context: Context) {

    private val realm = Realm.getDefaultInstance()

    private val sessionId: Long
        get() {
            return realm.where(Session::class.java).max("id")?.toLong()?.plus(1) ?: 0
        }

    val prefs = Preferences(context)

    val timer by lazy {
        ProgressTimer(600).apply {
            onUpdate = onUpdateTimer
        }
    }

    val adapter = LapListAdapter(context, laps)

    var onUpdateTimer: (Float) -> Unit = { }

    val ratingNeededSessions: List<Session>
        get() {
            return realm.where(Session::class.java).equalTo("rating", -1f).findAll()
        }

    val laps: List<Lap>
        get() {
            return realm.where(Sake::class.java)
                    .equalTo("sessionId", sessionId)
                    .findAll()
                    .mapIndexed(::Lap)
                    .reversed()
        }

    var visibilityStopButton: (Int) -> Unit = { }

    val onClickStopButton = {
        timer.stop()
        writeItem(timer.startedAt, timer.endedAt, prefs.abv, prefs.volume)
        closeSession(timer.startedAt, Date())
        adapter.refreshItems(laps)
        visibilityStopButton(GONE)
    }

    fun onClickRecordButton(isRunning: Boolean) {
        if (isRunning) {
            timer.stop()
            writeItem(timer.startedAt, timer.endedAt, prefs.abv, prefs.volume)
            adapter.refreshItems(laps)
        } else {
            timer.start()
            visibilityStopButton(VISIBLE)
        }
    }

    fun closeSession(openedAt: Date, closedAt: Date) {
        val id = sessionId
        realm.executeTransaction {
            it.createObject(Session::class.java, id).apply {
                this.openedAt = openedAt
                this.closedAt = closedAt
            }
        }
    }

    fun writeItem(startedAt: Date, endedAt: Date, abv: Float, volume: Int) {
        val id = sessionId
        realm.executeTransaction {
            it.createObject(Sake::class.java).apply {
                this.sessionId = id
                this.startedAt = startedAt
                this.endedAt = endedAt
                this.abv = abv
                this.volume = volume
            }
        }
    }
}