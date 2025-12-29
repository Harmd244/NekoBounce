package io.qzz.nekobounce.features.module.modules.player
import io.qzz.nekobounce.event.UpdateEvent
import io.qzz.nekobounce.event.handler
import io.qzz.nekobounce.features.module.Category
import io.qzz.nekobounce.features.module.Module

object Hub : Module("Hub", Category.PLAYER, gameDetecting = false) {

    val onUpdate = handler<UpdateEvent> {
        val thePlayer = mc.thePlayer ?: return@handler

        thePlayer.sendChatMessage("/hub")

        state = false
    }
}