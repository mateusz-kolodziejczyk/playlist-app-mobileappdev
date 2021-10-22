package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
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
        print(playlists.playlistsProperty.values.size)
    }

    fun deleteOne(id: Long) {
        playlists.deleteOne(id)
    }

    fun update(playlist: Playlist){
        playlists.update(playlist)
    }

    fun deleteSongFromPlaylists(songID: Long){
        playlists.deleteSongFromAll(songID)
    }

}