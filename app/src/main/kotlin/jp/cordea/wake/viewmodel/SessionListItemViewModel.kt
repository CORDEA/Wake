package jp.cordea.wake.viewmodel

import io.realm.Sort
import jp.cordea.wake.model.Session

/**
 * Created by Yoshihiro Tanaka on 2017/03/18.
 */
class SessionListItemViewModel : DataListItemViewModel() {

    val sessionData: List<Session>
        get() {
            return realm.where(Session::class.java).findAllSorted("id", Sort.DESCENDING)
        }

}