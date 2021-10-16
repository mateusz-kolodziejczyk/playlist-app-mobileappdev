package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.song.Song

class SongView() {
    fun runSongMenu(): Int {
        var option: Int = 0
        var input: String?
        // The main menu allows the user to go to one of the submenus
        println("MAIN MENU")
        println(" 1. Add Song")
        println(" 2. List all Songs")
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

    fun addSong() : Song? {
        println("Add a Song")
        // Ask the user whether they want to see a full list of artists
        print("\nDo you wish to see a full list of artists? (Y/N): ")
        var option = readLine()!!.uppercase()
        if(option == "Y") {

        }
        print("\nArtist id: ")
        var artistId = readLine()!!.toLong()
        // Check to see that an artist with that ID exists
        print("\nSong Name: ")
        var artist = Artist()
        var name = readLine()!!
        print("\nYear of Release: ")
        var year = readLine()!!
        var newSong = Song(name, year, artist)
        return newSong
    }

    fun listAll(songs: MutableList<Song>){
        println("List of all Songs")
        println("")

    }
}
