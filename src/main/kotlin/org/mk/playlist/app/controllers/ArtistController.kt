package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.views.console.ArtistView
import mu.KotlinLogging
import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.SongStore

class ArtistController {
    private val logger = KotlinLogging.logger {}
    var view = ArtistView()
    fun run(artists : ArtistStore, songs:SongStore, playlists: PlaylistStore){
        var option = 0
        do {
            var option = view.runArtistMenu()
            when (option) {
                1 -> add(view.addArtist(), artists)
                2 -> view.listAll(artists.findAll())
                3 -> search(artists)
                4 -> deleteOne(artists, songs, playlists)
            }
        } while (option != -1)
    }
    private fun add(artist: Artist?, artistStore: ArtistStore){
        if(artist != null){
            artistStore.add(artist)
        }
        else{
            logger.info("\nArtist not added")
        }
    }
    private fun search(artists: ArtistStore){
        val artist = view.findArtist(artists)
        if(artist != null){
            view.showArtistDetails(artist)
        }
    }
    private fun deleteOne(artists: ArtistStore, songs: SongStore, playlists: PlaylistStore){
        view.listAll(artists.findAll())
        val artist = view.findArtist(artists)
        if(artist != null){
            // Delete all playlists containing songs by this artist,
            // then delete all songs by this artist before deleting it
            val songsToDelete = songs.filter { it.artist.id == artist.id }
            for(song in songsToDelete){
                // Delete from playlists then delete the song
                playlists.deleteSongFromAll(song.id)
                songs.deleteOne(song.id)
            }
            artists.deleteOne(artist.id)
        }

    }

}