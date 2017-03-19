package jp.cordea.wake.viewmodel

import io.realm.Realm
import jp.cordea.wake.model.Sake
import jp.cordea.wake.model.Session

/**
 * Created by Yoshihiro Tanaka on 2017/03/15.
 */
open class DataListItemViewModel {

    protected val realm = Realm.getDefaultInstance()

    protected fun getSessionTime(session: Session): Float {
        return (session.closedAt.time - session.openedAt.time) / 1000f
    }

    protected fun getVolume(sake: Sake): Float {
        return sake.abv * (sake.volume / 100f)
    }
}