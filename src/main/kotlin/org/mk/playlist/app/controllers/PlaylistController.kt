package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.views.console.PlaylistView

class PlaylistController {
    private var view = PlaylistView()
    fun run(songs: SongStore, playlists: PlaylistStore){
        var option = 0
        do {
            option = view.runPlaylistMenu()
            when (option) {
                1 -> create(playlists)
                2 -> view.listAllPlaylists(playlists.findAll())
                3 -> add(playlists, songs)
                4 -> search(playlists, songs)
                5 -> deleteOne(playlists)
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

    private fun search(playlists: PlaylistStore, songs: SongStore){
        val playlist = view.findPlaylist(playlists)
        if(playlist != null){
            // Create a map out of the songs list
            view.showPlaylistDetails(playlist, songs.findAll().associateBy { it.id })
        }
    }

    private fun deleteOne(playlists: PlaylistStore){
        println("Delete a single Playlist")
        view.listAllPlaylists(playlists.findAll())
        val playlist = view.findPlaylist(playlists)
        if(playlist != null){
            playlists.deleteOne(playlist.id)
        }
    }
}