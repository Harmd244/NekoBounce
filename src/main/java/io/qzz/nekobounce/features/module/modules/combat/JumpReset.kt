/*
 * NekoBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/RouQingNeko1024/NekoBounce
 * Code By GoldBounce,Lizz,NightSky,FDP
 * https://github.com/SkidderMC/FDPClient
 * https://github.com/qm123pz/NightSky-Client
 * https://github.com/bzym2/GoldBounce/
 */
package io.qzz.nekobounce.features.module.modules.combat

import io.qzz.nekobounce.event.UpdateEvent
import io.qzz.nekobounce.event.handler
import io.qzz.nekobounce.features.module.Category
import io.qzz.nekobounce.features.module.Module
import io.qzz.nekobounce.utils.extensions.isInLiquid
import io.qzz.nekobounce.utils.extensions.tryJump
import io.qzz.nekobounce.utils.kotlin.RandomUtils

object JumpReset : Module("JumpReset", Category.COMBAT) {
    val hurttime by int("HurtTime", 10, 0..10) {!randomHurttime}

    val randomHurttime by boolean("RandomHurtTime", false)
    var polarhurttime = hurttime

    override fun onEnable() {
        polarhurttime = if (randomHurttime) {
            RandomUtils.nextInt(8, 10)
        } else {
            hurttime
        }
    }

    val onUpdate = handler<UpdateEvent> {
        val thePlayer = mc.thePlayer ?: return@handler
        if (thePlayer.isInLiquid || thePlayer.isInWeb || thePlayer.isDead)
            return@handler

        if (thePlayer.hurtTime == polarhurttime) {
            thePlayer.tryJump()

            polarhurttime = if (randomHurttime) {
                RandomUtils.nextInt(8,10)
            } else {
                hurttime
            }
        }
    }
}