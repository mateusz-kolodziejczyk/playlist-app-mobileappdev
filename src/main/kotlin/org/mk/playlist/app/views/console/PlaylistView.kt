package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.utilities.getId
import org.mk.playlist.app.utilities.listAllSongs

class PlaylistView {
    fun runPlaylistMenu() : Int{
        val input : String?
        var option = 0
        // The main menu allows the user to go to one of the submenus
        println("\nPLAYLIST MENU")
        println(" 1. Create Playlist")
        println(" 2. List all Playlists")
        println(" 3. Add to Playlist")
        println(" 4. Show Playlist Details")
        println(" 5. Delete a Playlist")
        println(" 6. Delete a Song from a Playlist")
        println(" 7. Update Playlist Name")
        println(" 8. Find all Playlists with a particular Song")
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

    fun createPlaylist() : Playlist?{
        println("\nCreate a Playlist")
        val name = readInPlaylistName()
        return if(name.isNotEmpty()){
            println("Playlist with name [$name] added")
            Playlist(name = name)
        } else{
            println("Playlist not added.")
            null
        }
    }

    private fun readInPlaylistName() : String{
        print("\nEnter a name: ")
        return readLine()!!
    }

    fun showPlaylistDetails(playlist: Playlist, songs: Map<Long, Song>){
        println("\nFull details for: [${playlist.id}: ${playlist.name}]")
        println("Songs in playlist:")
        for(songId in playlist.songs){
            val song = songs[songId]
            if(song != null){
                println("$songId: ${song.title}, ${song.year}")
            }
        }
    }

    fun addToPlaylist(playlists: PlaylistStore, songs:SongStore) : SongPlaylist?{
        println("\nAdd songs to a playlist")
        listAllPlaylists(playlists.findAll())
        listAllSongs(songs)
        var song : Song? = null
        var playlist : Playlist? = null
        while(song == null){
            println("\nChoose a playlist to add to")
            playlist = findPlaylist(playlists)
            if(playlist == null){
                println("Playlist not found")
                break
            }
            song = findSong(songs)
            if(song == null){
                println("Song not found")
                break
            }
        }
        // Return null if either of them are still null
        if(song == null || playlist == null){
            return null
        }
        return SongPlaylist(playlist = playlist, song = song)
    }

    fun findPlaylist(playlists: PlaylistStore) : Playlist? {
        print("\nEnter playlist ID: ")
        val id = getId()
        if(id == null){
            println("Could not find playlist.")
            println("Playlist ID has to be a number")
            return null
        }
        else if (id == -1L){
            return null
        }
        return playlists.findOne(id)
    }

    fun findSong(songs: SongStore) : Song? {
        print("\nEnter song ID: ")
        val id = getId()
        if(id == null){
            println("Could not find song.")
            println("Song ID has to be a number")
            return null
        }
        else if (id == -1L){
            return null
        }
        return songs.findOne(id)
    }
    fun findAllWithSong(playlists: PlaylistStore, songs: SongStore){
        print("\nEnter Song ID: ")
        val songID = getId()
        if(songID != null){
            val song = songs.findOne(songID)
            if(song != null){
                println("Playlists with song [${song.id}: ${song.title}(${song.year})]")
                playlists.findAllWithSong(songID).forEach{ playlist -> println("${playlist.id}: ${playlist.name}") }
            }
            else{
                println("Song does not exist")
            }
        }
    }
    fun listAllPlaylists(playlists: List<Playlist>){
        println("\nList of all Playlists")
        playlists.forEach { playlist -> println("${playlist.id}: ${playlist.name}") }
    }

    fun updateName(playlists: PlaylistStore): Playlist?{
        println("\nUpdate playlist name")
        val playlist = findPlaylist(playlists)
        return if(playlist != null){
            println("Current name: [${playlist.name}]")
            val newName = readInPlaylistName()
            if(newName.isNotEmpty()){
                println("Successfully updated playlist")
                Playlist(id = playlist.id, name = newName)
            } else{
                println("Playlist not updated")
                null
            }
        } else{
            println("Playlist not found")
            null
        }
    }
}

data class SongPlaylist(val playlist : Playlist = Playlist(), val song : Song = Song())
