package org.mk.playlist.app.utilities

import org.mk.playlist.app.models.artist.ArtistStore

fun listAllArtists(artistStore: ArtistStore){
    println("List of all Artists")
    artistStore.findAll().forEach{artist -> println(artist)}
}