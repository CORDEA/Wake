package jp.cordea.wake.viewmodel

import android.content.Context
import jp.cordea.wake.model.Preferences

/**
 * Created by Yoshihiro Tanaka on 2017/03/14.
 */
class SettingsViewModel(context: Context) {

    private val prefs = Preferences(context)

    val referenceDescription = "%d sec".format(prefs.reference)

    val alcoholDescription = "%d ml, %f %% ABV".format(prefs.volume, prefs.abv)

}