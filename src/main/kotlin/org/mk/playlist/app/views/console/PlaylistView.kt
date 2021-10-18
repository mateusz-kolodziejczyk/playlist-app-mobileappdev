package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.playlist.Playlist

class PlaylistView {
    fun runPlaylistMenu() : Int{
        val input : String?
        var option = 0
        // The main menu allows the user to go to one of the submenus
        println("PLAYLIST MENU")
        println(" 1. Create Playlist")
        println(" 2. Add to Playlist")
        println(" 3. List all Playlists")
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
        println("Create a Playlist")
        print("\n\nName: ")
        val name = readLine()!!
        return if(name.isNotEmpty()){
            println("Playlist with name [$name] added")
            Playlist(name = name)
        } else{
            println("Playlist not added.")
            null
        }
    }

    fun addToPlaylist(){

    }

    fun listAllPlaylists(playlists: MutableList<Playlist>){
        playlists.forEach { playlist -> print("\n${playlist.id}: ${playlist.name}") }
    }
}