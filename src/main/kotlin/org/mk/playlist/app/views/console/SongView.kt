package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistStore
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.utilities.getId
import org.mk.playlist.app.utilities.listAllArtists

class SongView {
    fun runSongMenu(): Int {
        var option: Int = 0
        val input: String?
        // The main menu allows the user to go to one of the submenus
        println("\nSong Menu")
        println(" 1. Add Song")
        println(" 2. List all Songs")
        println(" 3. Find a Song")
        println(" 4. Delete a Song")
        println(" 5. Update Song Details")
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

    fun addSong(artists: ArtistStore): Song? {
        println("\nAdd a Song")
        // Quit out immediately if there are no valid artists.
        if (artists.isEmpty()) {
            println("There are no artists in the database. Please add an artist before adding a song.")
            return null
        }

        // List all the artists
        listAllArtists(artists)

        var artistId : Long? = null
        var artist: Artist? = null
        // Do the loop until a valid artist has been found, or the user decides to cancel.
        while (artist == null || artistId == -1L) {
            print("\nAdd an artist by ID. Input -1 for ID to cancel adding a song.")
            print("\nArtist id: ")
            artistId = getId()
            if (artistId == null) {
                print("\nInvalid ID")
                break
            }
            if (artistId == -1L) {
                return null
            }
            // Try to find the artist using the id provided
            // The while loop will stop if an artist was found
            artist = artists.findOne(artistId)
            if (artist == null) {
                print("\n\nNo artist was found with id [$artistId]")
            }
        }
        if (artist == null) {
            return null
        }
        print("\nSong Name: ")
        val name = readLine()!!
        print("\nYear of Release: ")
        val year = readLine()!!
        return if(artistId == null){
            null
        } else{
            Song(name, year, artistId)
        }
    }

    fun showSongDetails(song: Song, artist: Artist){
        println("${song.id}: ${song.title} by [$artist] in ${song.year}")
    }

    fun findSong(songs: SongStore) : Song? {
        print("\nSong ID: ")
        val id = getId()
        if(id == null){
            return null
        }
        else if (id == -1L){
            return null
        }
        return songs.findOne(id)
    }

    fun listAll(songs: List<Song>, artists: Map<Long, Artist>) {
        println("\nList of all Songs")
        for(song in songs){
            val artist = artists[song.artistId]
            if(artist != null){
                showSongDetails(song, artist)
            }
            else{
                showSongDetails(song, Artist())
            }
        }
    }

    fun updateSongDetails(songs: SongStore, artists: ArtistStore) : Song? {
        println("\nUpdate song details.")
        println("Leave the space blank to not update a field")
        val song = findSong(songs)
        if(song != null){
            print("\nEnter a new title for [${song.title}]: ")
            var newTitle = readLine()!!
            if(newTitle.isEmpty()){
                newTitle = song.title
            }
            print("\nEnter a new year for [${song.year}]: ")
            var newYear = readLine()!!
            if(newYear.isEmpty()){
                newYear = song.year
            }
            listAllArtists(artists)
            print("\nEnter a new artist id for [${song.artistId}]: ")
            val newArtistId = try{
                readLine()!!.toLong()
            } catch(exception: NumberFormatException) {
                song.artistId
            }
            println("New Song details: [Title: $newTitle, Year: $newYear, Artist: [${artists.findOne(newArtistId)}]]")
            return Song(id = song.id, title = newTitle, year = newYear, artistId = newArtistId)
        }
        else{
            println("Song not found")
            return null
        }

    }
}
