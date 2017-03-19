package jp.cordea.wake.viewmodel

import android.content.Context
import android.text.Editable
import jp.cordea.wake.model.Preferences

/**
 * Created by Yoshihiro Tanaka on 2017/03/17.
 */
class ProgressDialogViewModel(context: Context) {

    private val prefs = Preferences(context)

    fun setProgress(progress: Editable) {
        val value = progress.toString().toInt()
        prefs.reference = value
    }

}