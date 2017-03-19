package jp.cordea.wake.model

import io.realm.RealmObject
import java.util.*

/**
 * Created by Yoshihiro Tanaka on 2017/03/09.
 */
open class Sake : RealmObject() {

    open var sessionId: Long = 0L

    open var startedAt: Date = Date()

    open var endedAt: Date = Date()

    open var abv: Float = 0f

    open var volume: Int = 0

}