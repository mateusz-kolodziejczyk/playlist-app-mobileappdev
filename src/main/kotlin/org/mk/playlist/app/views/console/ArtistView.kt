package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist

class ArtistView() {
    fun runArtistMenu(): Int {
        var option: Int = 0
        var input: String?
        // The main menu allows the user to go to one of the submenus
        println("ARTIST MENU")
        println(" 1. Add Artist")
        println(" 2. List all Artists")
        println("-1. Return to Main Menu")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun addArtist() : Artist? {
        println("Add an Artist")
        print("\nFirst Name: ")
        var firstName = readLine()!!
        print("\nLast Name: ")
        var lastName = readLine()!!
        // Using the data validation, the artist will be null if either name field is empty.
        var newArtist = Artist(firstName, lastName)
        if (newArtist != null) {
            println("Artist added [$firstName $lastName]")
        } else {
            println("Artist not added, not all fields were filled out.")
        }
        return newArtist
    }

    fun listAll(artists: MutableList<Artist>){
        println("List of all Artists")
        println("")
        for(artist in artists){
            println("${artist.id}: ${artist.firstName} ${artist.lastName}")
        }
    }
}


