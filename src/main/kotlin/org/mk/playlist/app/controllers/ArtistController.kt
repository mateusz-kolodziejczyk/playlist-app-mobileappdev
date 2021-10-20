package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.views.console.ArtistView
import mu.KotlinLogging
import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.SongStore

class ArtistController {
    private val logger = KotlinLogging.logger {}
    private var view = ArtistView()

    private lateinit var artists: ArtistStore
    private lateinit var songs: SongStore
    private lateinit var playlists: PlaylistStore

    fun run(artists: ArtistStore, songs: SongStore, playlists: PlaylistStore) {
        this.artists = artists
        this.songs = songs
        this.playlists = playlists

        var option: Int
        do {
            option = view.runArtistMenu()
            when (option) {
                1 -> add(view.addArtist())
                2 -> view.listAll(artists.findAll())
                3 -> search()
                4 -> deleteOne()
            }
        } while (option != -1)
    }

    private fun add(artist: Artist?) {
        if (artist != null) {
            artists.add(artist)
        } else {
            logger.info("\nArtist not added")
        }
    }

    private fun search() {
        val artist = view.findArtist(artists)
        if (artist != null) {
            view.showArtistDetails(artist)
        }
    }

    private fun deleteOne() {
        view.listAll(artists.findAll())
        val artist = view.findArtist(artists)
        if (artist != null) {
            // Delete all playlists containing songs by this artist,
            // then delete all songs by this artist before deleting it
            val songsToDelete = songs.filter { it.artistId == artist.id }
            for (song in songsToDelete) {
                // Delete from playlists then delete the song
                playlists.deleteSongFromAll(song.id)
                songs.deleteOne(song.id)
            }
            artists.deleteOne(artist.id)
        }

    }

}