package jp.cordea.wake.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Yoshihiro Tanaka on 2017/03/09.
 */
open class Session : RealmObject() {

    @PrimaryKey
    open var id: Long = 0

    open var openedAt: Date = Date()

    open var closedAt: Date = Date()

    open var rating: Float = -1f

    val sessionTime: Long
        get() {
            return ((closedAt.time - openedAt.time) / 1000f).toLong()
        }

    val isNeedRating: Boolean
        get() {
            if (rating != -1f) {
                return false
            }
            return Calendar.getInstance().apply {
                time = closedAt
                set(get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DATE) + 1, 6, 0, 0)
            }.before(Calendar.getInstance())
        }

}