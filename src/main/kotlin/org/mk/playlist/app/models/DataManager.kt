package org.mk.playlist.app.models

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song

// This class will manage the data relevant to the program, including I/O
class DataManager (var artists: ArrayList<Artist>, var songs: ArrayList<Song>, var playlists: ArrayList<Playlist>){

}