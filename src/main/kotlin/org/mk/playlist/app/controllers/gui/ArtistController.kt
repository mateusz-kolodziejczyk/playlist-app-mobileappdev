package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistJSONStore
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.Controller

class ArtistController : Controller() {
    val logger = KotlinLogging.logger{}
    val artists = ArtistJSONStore()
    fun add(firstName: String, lastName: String){
        artists.add(Artist(firstName = firstName, lastName = lastName))
    }

    fun deleteOne(id: Long) {
        TODO()
    }

}