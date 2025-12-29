package io.qzz.nekobounce.features.module.modules.combat

import io.qzz.nekobounce.event.AttackEvent
import io.qzz.nekobounce.event.UpdateEvent
import io.qzz.nekobounce.event.handler
import io.qzz.nekobounce.features.module.Category
import io.qzz.nekobounce.features.module.Module
import io.qzz.nekobounce.utils.client.PacketUtils.sendPacket
import net.minecraft.entity.EntityLivingBase
import net.minecraft.network.play.client.C0BPacketEntityAction

object MoreKB : Module("MoreKB", Category.COMBAT) {

    private val mode by choices("Mode", arrayOf("LegitFast", "Packet"), "LegitFast")
    private val onlyGround by boolean("OnlyGround", true)

    private var target: EntityLivingBase? = null
    private var ticks = 0

    val onAttack = handler<AttackEvent> { event ->
        val thePlayer = mc.thePlayer ?: return@handler
        val targetEntity = event.targetEntity

        if (targetEntity is EntityLivingBase) {
            target = targetEntity
            ticks = 2
        }
    }

    val onUpdate = handler<UpdateEvent> {
        val thePlayer = mc.thePlayer ?: return@handler

        if (target != null && ticks > 0) {
            if (onlyGround && !thePlayer.onGround) {
                target = null
                ticks = 0
                return@handler
            }

            // 检查玩家是否在移动
            val isMoving = thePlayer.motionX != 0.0 || thePlayer.motionZ != 0.0
            
            if (isMoving) {
                when (mode) {
                    "LegitFast" -> thePlayer.sprintingTicksLeft = 0
                    "Packet" -> {
                        sendPacket(C0BPacketEntityAction(thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING))
                        sendPacket(C0BPacketEntityAction(thePlayer, C0BPacketEntityAction.Action.START_SPRINTING))
                    }
                }
            }

            ticks--
            if (ticks <= 0) {
                target = null
            }
        }
    }

    override val tag
        get() = mode
}