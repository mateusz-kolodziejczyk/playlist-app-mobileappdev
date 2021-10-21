package org.mk.playlist.app.utilities

import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.models.song.SongStore

fun listAllArtists(artistStore: ArtistStore){
    println("List of all Artists")
    artistStore.findAll().forEach{artist -> println(artist)}
}

fun listAllSongs(songs: SongStore) {
    println("\nList of all songs")
    songs.findAll().forEach { song -> println("${song.id}: ${song.title}(${song.year})") }
}