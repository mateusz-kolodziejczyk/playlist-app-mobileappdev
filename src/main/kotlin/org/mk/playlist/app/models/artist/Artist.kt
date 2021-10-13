package org.mk.playlist.app.models.artist

data class Artist (
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = ""
    )

fun validateArtist(firstName: String, lastName: String) : Boolean{
    if(firstName.isNotEmpty() && lastName.isNotEmpty()){
        return true
    }
    return false
}