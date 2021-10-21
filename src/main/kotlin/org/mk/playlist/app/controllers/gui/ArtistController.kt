package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.ArtistJSONStore
import tornadofx.Controller

class ArtistController : Controller() {
    val logger = KotlinLogging.logger{}
    val artists = ArtistJSONStore()


}