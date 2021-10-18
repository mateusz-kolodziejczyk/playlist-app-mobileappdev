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
                1 -> createPlaylist(playlists)
                2 -> view.listAllPlaylists(playlists.findAll())
                //3 -> addToPlaylist()
            }
        } while (option != -1)
    }

    fun createPlaylist(playlists: PlaylistStore){
        val playlist = view.createPlaylist()
        if(playlist != null){
            playlists.add(playlist)
        }
    }

    fun addToPlaylist() {

    }
}