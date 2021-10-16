package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistMemStore
import org.mk.playlist.app.models.artist.validateArtist

class ArtistView() {
    fun runArtistMenu(): Int {
        var option: Int = 0
        var input: String?
        // The main menu allows the user to go to one of the submenus
        println("MAIN MENU")
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
        println("First Name: ")
        var firstName = readLine()!!
        println("Last Name: ")
        var lastName = readLine()!!
        if (validateArtist(firstName, lastName)) {
            return Artist(firstName = firstName, lastName = lastName)
            println("Artist added [$firstName $lastName]")
        } else {
            return null
            println("Artist not added, not all fields were filled out.")
        }
    }
}

