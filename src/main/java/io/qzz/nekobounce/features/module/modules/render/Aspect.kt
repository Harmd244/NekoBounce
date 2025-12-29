/*
 * This file is part of NekoBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * NekoBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NekoBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NekoBounce. If not, see <https://www.gnu.org/licenses/>.
 *
 */
//打滑 NekoBounce NextGen

package io.qzz.nekobounce.features.module.modules.render

import io.qzz.nekobounce.features.module.Category
import io.qzz.nekobounce.features.module.Module

/**
 * Aspect ratio changer
 *
 * @author sqlerrorthing
 */
object Aspect : Module("Aspect", Category.RENDER) {
    private val ratioPercentage by int("Ratio", 100, 1..300)

    val ratioMultiplier: Float
        get() = ratioPercentage / 100.0f
}