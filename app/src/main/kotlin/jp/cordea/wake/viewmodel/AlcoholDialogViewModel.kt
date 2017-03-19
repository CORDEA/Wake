package jp.cordea.wake.viewmodel

import android.content.Context
import android.text.Editable
import jp.cordea.wake.model.Preferences

/**
 * Created by Yoshihiro Tanaka on 2017/03/17.
 */
class AlcoholDialogViewModel(context: Context) {

    private val prefs = Preferences(context)

    fun setAbv(abv: Editable) {
        val value = abv.toString().toFloat()
        prefs.abv = value
    }

    fun setVolume(volume: Editable) {
        val value = volume.toString().toInt()
        prefs.volume = value
    }

}