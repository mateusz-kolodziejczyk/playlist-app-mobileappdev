package org.mk.playlist.app.models.song

import org.mk.playlist.app.models.artist.Artist

data class Song (
    var id: Long = 0,
    var title: String = "",
    var artist: Artist = Artist(),
    )