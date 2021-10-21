package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.views.console.PlaylistView

class PlaylistController {
    private var view = PlaylistView()

    private lateinit var songs: SongStore
    private lateinit var playlists: PlaylistStore

    fun run(songs: SongStore, playlists: PlaylistStore) {
        this.songs = songs
        this.playlists = playlists

        var option : Int
        do {
            option = view.runPlaylistMenu()
            when (option) {
                1 -> create()
                2 -> view.listAllPlaylists(playlists.findAll())
                3 -> add()
                4 -> search()
                5 -> deleteOne()
                6 -> deleteSongFromPlaylist()
                7 -> updateName()
            }
        } while (option != -1)
    }

    private fun create() {
        val playlist = view.createPlaylist()
        if (playlist != null) {
            playlists.add(playlist)
        }
    }

    private fun add() {
        val songPlaylist = view.addToPlaylist(playlists, songs)
        if (songPlaylist == null) {
            return
        } else {
            playlists.addToPlaylist(songPlaylist.playlist.id, songPlaylist.song)
        }
    }

    private fun search() {
        view.listAllPlaylists(playlists.findAll())
        val playlist = view.findPlaylist(playlists)
        if (playlist != null) {
            showDetails(playlist)
        }
    }

    private fun deleteSongFromPlaylist() {
        println("Delete a song from a Playlist")
        view.listAllPlaylists(playlists.findAll())
        val playlist = view.findPlaylist(playlists)
        if(playlist != null){
            showDetails(playlist)
            println("\nChose a song to delete")
            val song = view.findSong(songs)
            if(song != null){
                playlists.deleteSongFromOne(playlist.id, song.id)
            }
        }
    }

    private fun showDetails(playlist: Playlist){
        // Create a map out of the songs list
        view.showPlaylistDetails(playlist, songs.findAll().associateBy { it.id })
    }

    private fun deleteOne() {
        println("Delete a single Playlist")
        view.listAllPlaylists(playlists.findAll())
        val playlist = view.findPlaylist(playlists)
        if (playlist != null) {
            playlists.deleteOne(playlist.id)
        }
    }

    private fun updateName() {
        view.listAllPlaylists(playlists.findAll())
        val newPlaylistDetials = view.updateName(playlists)
        if(newPlaylistDetials != null){
            playlists.update(newPlaylistDetials)
        }
    }

}