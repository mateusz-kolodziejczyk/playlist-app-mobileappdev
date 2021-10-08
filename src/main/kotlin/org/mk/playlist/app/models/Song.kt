package org.mk.playlist.app.models

data class Song (
    var id: Long = 0,
    var title: String = "",
    var artist: Artist = Artist(),
    )