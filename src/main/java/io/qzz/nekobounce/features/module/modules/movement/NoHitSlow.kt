package io.qzz.nekobounce.features.module.modules.movement

import io.qzz.nekobounce.event.EventState
import io.qzz.nekobounce.event.MotionEvent
import io.qzz.nekobounce.event.handler
import io.qzz.nekobounce.features.module.Category
import io.qzz.nekobounce.features.module.Module

object NoHitSlow : Module("NoHitSlow", Category.MOVEMENT) {

    val onMotion = handler<MotionEvent> { event ->
        if (event.eventState == EventState.PRE) {
            val thePlayer = mc.thePlayer ?: return@handler
            
            if (thePlayer.hurtTime > 0) {
                thePlayer.speedInAir = 0.02f
            }
        }
    }
}