package org.mk.playlist.app.main

import mu.KotlinLogging
import org.mk.playlist.app.console.runMainMenu
import org.mk.playlist.app.models.Artist
import org.mk.playlist.app.models.Playlist
import org.mk.playlist.app.models.Song

private val logger = KotlinLogging.logger {}

var artists = ArrayList<Artist>()
var songs = ArrayList<Song>()
var playlists = ArrayList<Playlist>()
fun main(args: Array<String>){
    runMainMenu(artists, songs, playlists)
}