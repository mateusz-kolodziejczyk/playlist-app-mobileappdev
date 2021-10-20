package org.mk.playlist.app.models.artist

data class Artist (
    var id: Long = -1,
    var firstName: String = "Unknown",
    var lastName: String = "Artist"
    ){
    // Doing a companion object for data validation
    companion object{
        operator fun invoke(firstName: String, lastName: String) : Artist?{
            // If either name field is blank, return a null artist
            return if(firstName.isEmpty() || lastName.isEmpty()){
                null
            }
            else{
                Artist(firstName = firstName, lastName = lastName)
            }
        }
    }
    override fun toString() : String {
        return "$id: $firstName $lastName"
    }
}
