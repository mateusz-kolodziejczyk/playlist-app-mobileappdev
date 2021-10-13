package org.mk.playlist.app.main

import mu.KotlinLogging
import org.mk.playlist.app.views.console.runMainMenu
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song

private val logger = KotlinLogging.logger {}

var artists = ArrayList<Artist>()
var songs = ArrayList<Song>()
var playlists = ArrayList<Playlist>()
fun main(args: Array<String>){
    runMainMenu(artists, songs, playlists)
}