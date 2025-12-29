package io.qzz.nekobounce.features.module.modules.movement.nowebmodes

import io.qzz.nekobounce.utils.client.MinecraftInstance

open class NoWebMode(val modeName: String) : MinecraftInstance {
    open fun onUpdate() {}
}
