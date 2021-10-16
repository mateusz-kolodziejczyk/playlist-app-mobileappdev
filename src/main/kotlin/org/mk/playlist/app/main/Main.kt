package org.mk.playlist.app.main

import mu.KotlinLogging
import org.mk.playlist.app.controllers.MainMenuController


private val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    val mainMenuController = MainMenuController()
    mainMenuController.run()
}