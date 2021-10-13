package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.validateArtist

class ArtistView(){
    fun runArtistMenu(artists: ArrayList<Artist>){
        var option : Int = 0
        var input: String?
        do{
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
            artistMenuOptions(option, artists)
        }while(option != -1)

    }

    private fun artistMenuOptions(option: Int, artists: ArrayList<Artist>){
        when(option){
            1 -> addArtist(artists)
            // 2 -> listAllArtists(artists)
        }
    }

    private fun addArtist(artists:ArrayList<Artist>){
        println("Add an Artist")
        println("First Name: ")
        var firstName = readLine()!!
        println("Last Name: ")
        var lastName = readLine()!!
        if(validateArtist(firstName, lastName)){
            artists.add(Artist(id = artists.size.toLong(), firstName = firstName, lastName = lastName))
            println("Artist added [$firstName $lastName]")
        }
        else{
            println("Artist not added, not all fields were filled out.")
        }

    }
}


