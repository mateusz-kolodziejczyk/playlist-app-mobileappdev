package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.views.console.ArtistView
import mu.KotlinLogging

class ArtistController {
    private val logger = KotlinLogging.logger {}
    var artistView = ArtistView()
    fun run(artists : ArtistStore){
        var option = 0
        do {
            var option = artistView.runArtistMenu()
            when (option) {
                1 -> add(artistView.addArtist(), artists)
                2 -> artistView.listAll(artists.findAll())
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
}