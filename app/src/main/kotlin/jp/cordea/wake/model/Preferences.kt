package jp.cordea.wake.model

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by Yoshihiro Tanaka on 2017/03/10.
 */
class Preferences(context: Context) {

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    var abv: Float = 0f
        get() {
            return pref.getFloat(abvKey, 5.0f)
        }
        set(value) {
            field = value
            pref.edit().putFloat(abvKey, value).apply()
        }

    var volume: Int = 0
        get() {
            return pref.getInt(volumeKey, 500)
        }
        set(value) {
            field = value
            pref.edit().putInt(volumeKey, value).apply()
        }

    var reference: Int = 0
        get() {
            return pref.getInt(referenceKey, 600)
        }
        set(value) {
            field = value
            pref.edit().putInt(referenceKey, value).apply()
        }

    companion object {

        private val abvKey = "AbvKey"

        private val volumeKey = "VolumeKey"

        private val referenceKey = "ReferenceKey"

    }

}
