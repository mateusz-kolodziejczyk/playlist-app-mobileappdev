package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.playlist.PlaylistJSONStore
import org.mk.playlist.app.models.song.SongJSONStore
import tornadofx.Controller

class PlaylistController : Controller() {
    val logger = KotlinLogging.logger{}
    val playlists = PlaylistJSONStore()
    val songs = SongJSONStore()

    fun add(name: String){
        playlists.add(playlist = Playlist(name = name))
    }

    fun deleteOne(id: Long) {
        playlists.deleteOne(id)
    }

    fun update(playlist: Playlist){
        playlists.update(playlist)
    }
}