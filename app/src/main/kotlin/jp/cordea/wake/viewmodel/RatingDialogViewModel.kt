package jp.cordea.wake.viewmodel

import io.realm.Realm
import jp.cordea.wake.model.Session

/**
 * Created by Yoshihiro Tanaka on 2017/03/19.
 */
class RatingDialogViewModel(private val sessionId: Long) {

    private val realm = Realm.getDefaultInstance()

    var rating: Float
        set(value) {
            val session = realm.where(Session::class.java).equalTo("id", sessionId).findFirst()
            realm.executeTransaction {
                session.rating = value
            }
        }
        get() {
            val session = realm.where(Session::class.java).equalTo("id", sessionId).findFirst()
            return session.rating
        }

}