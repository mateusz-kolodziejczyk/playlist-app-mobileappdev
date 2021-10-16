package org.mk.playlist.app.controllers

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.views.console.ArtistView
import org.mk.playlist.app.views.console.SongView

class SongController {
    private val logger = KotlinLogging.logger {}
    var songView = SongView()
    fun run(artists : ArtistStore, songs: SongStore){
        var option = 0
        do {
            var option = songView.runSongMenu()
            when (option) {
            }
        } while (option != -1)
    }
    private fun add(song: Song?, songStore: SongStore){
        if(song != null){
            songStore.add(song)
        }
        else{
            logger.info("Artist not added")
        }
    }
}