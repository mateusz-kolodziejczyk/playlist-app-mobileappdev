package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.playlist.PlaylistStore
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongStore
import org.mk.playlist.app.utilities.getId

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
        print("Name: ")
        val name = readLine()!!
        return if(name.isNotEmpty()){
            println("Playlist with name [$name] added")
            Playlist(name = name)
        } else{
            println("Playlist not added.")
            null
        }
    }

    fun showPlaylistDetails(playlist: Playlist){
        println("${playlist.id}: ${playlist.name}")
        println("Songs in playlist:")
        playlist.songs.forEach{songId -> println(songId)}
        TODO("Add a way to get song details from just song id")
    }

    fun addToPlaylist(playlists: PlaylistStore, songs:SongStore) : SongPlaylist?{
        println("\nAdd songs to a playlist")
        listAllPlaylists(playlists.findAll())
        println("\nList of all songs")
        songs.findAll().forEach { song -> println("${song.id}: ${song.title}") }
        var song : Song? = null
        var playlist : Playlist? = null
        while(song == null){
            println("Type in -1 at any point to stop adding to a playlist")
            println("Choose a playlist to add to")
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
        print("\nPlaylist ID: ")
        val id = getId()
        if(id == null){
            return null
        }
        else if (id == -1L){
            return null
        }
        return playlists.findOne(id)
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

    fun listAllPlaylists(playlists: List<Playlist>){
        println("\nList of all Playlists")
        playlists.forEach { playlist -> println("${playlist.id}: ${playlist.name}") }
    }
}

data class SongPlaylist(val playlist : Playlist = Playlist(), val song : Song = Song())
