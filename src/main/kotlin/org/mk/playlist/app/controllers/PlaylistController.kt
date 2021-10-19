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
                1 -> create(playlists)
                2 -> view.listAllPlaylists(playlists.findAll())
                3 -> add(playlists, songs)
                4 -> search(playlists)
            }
        } while (option != -1)
    }

    private fun create(playlists: PlaylistStore){
        val playlist = view.createPlaylist()
        if(playlist != null){
            playlists.add(playlist)
        }
    }

    private fun add(playlists: PlaylistStore, songs: SongStore) {
        val songPlaylist = view.addToPlaylist(playlists, songs)
        if(songPlaylist == null){
            return
        }
        else{
            playlists.addToPlaylist(songPlaylist.playlist.id, songPlaylist.song)
        }
    }

    private fun search(playlists: PlaylistStore){
        val playlist = view.findPlaylist(playlists)
        if(playlist != null){
            view.showPlaylistDetails(playlist)
        }
    }
}