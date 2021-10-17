package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.views.console.PlaylistView

class PlaylistController {
    var view = PlaylistView()
    fun run(songs: SongStore, playlists: PlaylistStore){
        var option = 0
        do {
            var option = view.runPlaylistMenu()
            when (option) {
                1 -> createPlaylist()
                2 -> addToPlaylist()
                3 -> true
            }
        } while (option != -1)
    }

    fun createPlaylist(){

    }

    fun addToPlaylist(){

    }
}