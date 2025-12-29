package io.qzz.nekobounce.features.module.modules.render

import io.qzz.nekobounce.NekoBounce
import io.qzz.nekobounce.features.module.Category
import io.qzz.nekobounce.features.module.Module


object Camera : Module("Camera", Category.RENDER, gameDetecting = false) {

    val motionCamera = boolean("MotionCamera", true)
    val interpolation = float("MotionInterpolation", 0.05f, 0.01f..0.5f) { motionCamera.get()}
    override fun onEnable() {
        NekoBounce.moduleManager.getModule(Camera::class.java)?.state = false
    }
    override val tag
        get() = interpolation.get().toString()
}