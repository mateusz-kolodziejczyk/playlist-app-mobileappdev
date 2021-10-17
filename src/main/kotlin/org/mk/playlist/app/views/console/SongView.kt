package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.models.song.Song
import java.lang.NumberFormatException

class SongView() {
    fun runSongMenu(): Int {
        var option: Int = 0
        var input: String?
        // The main menu allows the user to go to one of the submenus
        println("\nSong Menu")
        println(" 1. Add Song")
        println(" 2. List all Songs")
        println(" 3. List all Artists")
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

    fun addSong(artists: ArtistStore): Song? {
        println("Add a Song")
        // Quit out immediately if there are no valid artists.
        if (artists.isEmpty()) {
            println("\nThere are no artists in the database. Please add an artist before adding a song.")
            return null
        }

        // Ask the user whether they want to see a full list of artists
        print("\nDo you wish to see a full list of artists? (Y/N): ")
        var option = readLine()!!.uppercase()
        if (option == "Y") {
            artists.findAll().forEach { artist -> println(artist) }
        }
        var artistId = -2L
        var artist: Artist? = null
        // Do the loop until a valid artist has been found, or the user decides to cancel.
        while (artist == null || artistId == -1L) {
            print("\nAdd an artist by ID. Input -1 for ID to cancel adding a song.")
            print("\nArtist id: ")
            try {
                artistId = readLine()!!.toLong()
            } catch (exception: NumberFormatException) {
                println("Not a valid id, please type in a number")
            }
            // Try to find the artist using the id provided
            // The while loop will stop if an artist was found
            artist = artists.findOne(artistId)
            if(artist == null){
                print("\n\nNo artist was found with id [$artistId]")
            }
        }

        print("\nSong Name: ")
        var name = readLine()!!
        print("\nYear of Release: ")
        var year = readLine()!!
        return Song(name, year, artist)
    }

    fun listAll(songs: MutableList<Song>){
        println("List of all Songs")
        println("")
        songs.forEach{song -> print("\n${song.id}: ${song.title} by ${song.artist} in ${song.year}")}
    }
}
