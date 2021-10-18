package org.mk.playlist.app.models.song

import org.mk.playlist.app.models.artist.Artist

data class Song (
    var id: Long = 0,
    var title: String = "",
    var year: String = "",
    var artist: Artist = Artist(id = -1, firstName = "Unknown", lastName = "Artist"), ){
    companion object{
        operator fun invoke(title: String, year: String, artist: Artist) : Song?{
            // If either name field is blank, return a null artist
            return if(title.isEmpty() || year.isEmpty()){
                null
            }
            else{
                Song(title = title, year = year, artist = artist)
            }
        }
    }
}