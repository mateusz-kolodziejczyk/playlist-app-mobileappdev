package org.mk.playlist.app.models.song

import org.mk.playlist.app.models.artist.Artist

// This is an intermediary data class that is used to make displaying the associated artist simpler.
// Not stored in JSON
data class SongWithArtist(var id: Long = 0, var title:String = "", var artist:Artist = Artist(),)
