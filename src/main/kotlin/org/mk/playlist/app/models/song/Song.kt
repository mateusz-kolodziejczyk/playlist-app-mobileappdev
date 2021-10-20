package org.mk.playlist.app.models.song

data class Song (
    var id: Long = -1L,
    var title: String = "",
    var year: String = "",
    var artistId: Long = -1L){
    companion object{
        operator fun invoke(title: String, year: String, artistId: Long) : Song?{
            // If either name field is blank, return a null artist
            return if(title.isEmpty() || year.isEmpty()){
                null
            }
            else{
                Song(title = title, year = year, artistId = artistId)
            }
        }
    }
}