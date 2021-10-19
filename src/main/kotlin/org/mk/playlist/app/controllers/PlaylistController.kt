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
                3 -> addToPlaylist(playlists, songs)
            }
        } while (option != -1)
    }

    private fun createPlaylist(playlists: PlaylistStore){
        val playlist = view.createPlaylist()
        if(playlist != null){
            playlists.add(playlist)
        }
    }

    private fun addToPlaylist(playlists: PlaylistStore, songs: SongStore) {
        val songPlaylist = view.addToPlaylist(playlists, songs)
        if(songPlaylist == null){
            return
        }
        else{
            playlists.addToPlaylist(songPlaylist.playlist.id, songPlaylist.song)
        }
    }
}