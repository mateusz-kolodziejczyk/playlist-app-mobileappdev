package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.ArtistJSONStore
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.SongJSONStore
import tornadofx.Controller

class SongController : Controller() {
    val logger = KotlinLogging.logger{}
    val songs = SongJSONStore()
    val artists = ArtistJSONStore()
    fun add(title: String){
        TODO()
    }

    fun deleteOne(id: Long) {
        TODO()
    }
}