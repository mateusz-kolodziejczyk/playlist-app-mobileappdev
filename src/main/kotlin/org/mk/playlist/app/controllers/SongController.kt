package org.mk.playlist.app.controllers

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.views.console.SongView

class SongController {
    private val logger = KotlinLogging.logger {}
    private var view = SongView()
    fun run(artists : ArtistStore, songs: SongStore, playlists: PlaylistStore){
        var option : Int
        do {
            option = view.runSongMenu()
            when (option) {
                1 -> add(view.addSong(artists), songs)
                2 -> listAll(songs, artists)
                3 -> search(songs, artists)
                4 -> deleteOne(songs, playlists, artists)
            }
        } while (option != -1)
    }
    private fun add(song: Song?, songStore: SongStore){
        if(song != null){
            songStore.add(song)
        }
        else{
            logger.info("Song not added")
        }
    }
    private fun search(songs: SongStore, artists: ArtistStore){
        val song = view.findSong(songs)
        if(song != null){
            val artist = artists.findOne(song.artistId)
            if (artist != null){
                view.showSongDetails(song, artist)
            }
            else{
                view.showSongDetails(song, Artist())
            }
        }

    }
    private fun listAll(songs: SongStore, artists: ArtistStore){
        view.listAll(songs.findAll(), artists.findAll().associateBy { it.id })
    }
    private fun deleteOne(songs: SongStore, playlists: PlaylistStore, artists: ArtistStore){
        println("Delete a song")
        view.listAll(songs.findAll(), artists.findAll().associateBy { it.id })
        val song = view.findSong(songs)
        // Delete all the instances of the song in every playlist first
        if(song != null){
            playlists.deleteSongFromAll(song.id)
            // Then delete the song itself
            songs.deleteOne(song.id)
        }
    }
}