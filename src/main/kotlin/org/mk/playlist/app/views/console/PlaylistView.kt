package org.mk.playlist.app.views.console

import org.mk.playlist.app.models.playlist.Playlist

class PlaylistView {
    fun runPlaylistMenu() : Int{
        val input : String?
        var option = 0
        // The main menu allows the user to go to one of the submenus
        println("\nPLAYLIST MENU")
        println(" 1. Create Playlist")
        println(" 2. List all Playlists")
        //println(" 3. Add to Playlist")
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

    fun addToPlaylist(){

    }

    fun listAllPlaylists(playlists: MutableList<Playlist>){
        println("\nList of all Playlists")
        playlists.forEach { playlist -> println("${playlist.id}: ${playlist.name}") }
    }
}