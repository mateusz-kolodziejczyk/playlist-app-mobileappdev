package org.mk.playlist.app.models.song

import org.mk.playlist.app.models.artist.Artist

data class Song (
    var id: Long = 0,
    var title: String = "",
    var year: String = "",
    var artist: Artist = Artist(), ){
        override fun toString() : String{
            return "$id: $title by $artist in $year"
        }
    }