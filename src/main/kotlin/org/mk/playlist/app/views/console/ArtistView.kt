package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.utilities.getId

class ArtistView {
    fun runArtistMenu(): Int {
        var option: Int = 0
        val input: String?
        // The main menu allows the user to go to one of the submenus
        println("\nARTIST MENU")
        println(" 1. Add Artist")
        println(" 2. List all Artists")
        println(" 3. Find an Artist")
        println(" 4. Remove an Artist")
        println("-1. Return to Main Menu")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && input.isNotEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun addArtist() : Artist? {
        println("\nAdd an Artist")
        print("First Name: ")
        val firstName = readLine()!!
        print("\nLast Name: ")
        val lastName = readLine()!!
        // Using the data validation, the artist will be null if either name field is empty.
        val newArtist = Artist(firstName, lastName)
        if (newArtist != null) {
            println("Artist added [$firstName $lastName]")
        } else {
            println("Artist not added, not all fields were filled out.")
        }
        return newArtist
    }

    fun showArtistDetails(artist: Artist){
        println(artist)
    }

    fun findArtist(artists: ArtistStore) : Artist? {
        print("\nArtist ID: ")
        val id = getId()
        if(id == null){
            return null
        }
        else if (id == -1L){
            return null
        }
        return artists.findOne(id)
    }

    fun listAll(artists: MutableList<Artist>){
        println("\nList of all Artists")
        for(artist in artists){
            println(artist)
        }
    }

}


