package org.mk.playlist.app.views.console

class PlaylistView {
    fun runPlaylistMenu() : Int{
        var option: Int = 0
        var input: String?
        // The main menu allows the user to go to one of the submenus
        println("PLAYLIST MENU")
        println(" 1. Create Playlist")
        println(" 2. Add to Playlist")
        println(" 3. List all Playlists")
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

    fun createPlaylist(){

    }

    fun addToPlaylist(){

    }

    fun listAllPlaylists(){

    }
}