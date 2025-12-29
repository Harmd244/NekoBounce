package io.qzz.nekobounce.features.command.special

import io.qzz.nekobounce.NekoBounce.commandManager
import io.qzz.nekobounce.chat.packet.packets.ServerRequestJWTPacket
import io.qzz.nekobounce.features.command.Command
import io.qzz.nekobounce.features.module.modules.misc.LiquidChat
import io.qzz.nekobounce.utils.io.MiscUtils
import io.qzz.nekobounce.utils.kotlin.StringUtils

object ChatTokenCommand : Command("chattoken") {

    /**
     * Execute commands with provided [args]
     */
    override fun execute(args: Array<String>) {
        if (args.size <= 1) {
            chatSyntax("chattoken <set/copy/generate>")
            return
        }

        when (args[1].lowercase()) {
            "set" -> {
                if (args.size > 2) {
                    LiquidChat.jwtToken = StringUtils.toCompleteString(args, 2)
                    LiquidChat.jwt = true

                    if (LiquidChat.state) {
                        LiquidChat.state = false
                        LiquidChat.state = true
                    }
                } else {
                    chatSyntax("chattoken set <token>")
                }
            }

            "generate" -> {
                if (!LiquidChat.state) {
                    chat("§cError: §7LiquidChat is disabled!")
                    return
                }

                LiquidChat.client.sendPacket(ServerRequestJWTPacket())
            }

            "copy" -> {
                if (LiquidChat.jwtToken.isEmpty()) {
                    chat("§cError: §7No token set! Generate one first using '${commandManager.prefix}chattoken generate'.")
                    return
                }

                MiscUtils.copy(LiquidChat.jwtToken)
                chat("§aCopied to clipboard!")
            }
        }
    }

    override fun tabComplete(args: Array<String>): List<String> {
        if (args.isEmpty())
            return emptyList()

        return when (args.size) {
            1 -> {
                arrayOf("set", "generate", "copy")
                    .map { it.lowercase() }
                    .filter { it.startsWith(args[0], true) }
            }

            else -> emptyList()
        }
    }

}