package jp.cordea.wake.model

/**
 * Created by Yoshihiro Tanaka on 2017/03/09.
 */
class Lap(val index: Int, sake: Sake) {

    val lapTime: Long = ((sake.endedAt.time - sake.startedAt.time) / 1000f).toLong()

}