package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.views.console.ArtistView
import mu.KotlinLogging

class ArtistController {
    private val logger = KotlinLogging.logger {}
    var view = ArtistView()
    fun run(artists : ArtistStore){
        var option = 0
        do {
            var option = view.runArtistMenu()
            when (option) {
                1 -> add(view.addArtist(), artists)
                2 -> view.listAll(artists.findAll())
                3 -> search(artists)
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
}