package org.mk.playlist.app.console

import org.mk.playlist.app.models.Artist
import org.mk.playlist.app.models.Playlist
import org.mk.playlist.app.models.Song

// Pass array lists used in main so that the menu can modify them
fun runMainMenu(artists: ArrayList<Artist>, songs: ArrayList<Song>,  playlists: ArrayList<Playlist>){
    var option : Int = 0
    var input: String?
    do{
        // The main menu allows the user to go to one of the submenus
        println("MAIN MENU")
        println(" 1. Playlists")
        println(" 2. Songs")
        println(" 3. Artists")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        mainMenuOptions(option)
    }while(option != -1)
}

private fun mainMenuOptions(option: Int){
    when(option){
        1 -> runPlaylistMenu()
        2 -> runSongMenu()
        3 -> runArtistMenu()
    }
}