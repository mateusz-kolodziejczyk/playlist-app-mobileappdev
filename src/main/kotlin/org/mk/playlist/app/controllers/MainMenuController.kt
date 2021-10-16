package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistMemStore
import org.mk.playlist.app.views.console.MainView

class MainMenuController {
    var view = MainView()
    var artistMemStore = ArtistMemStore()
    var artistController = ArtistController()
    fun run(){
        var option = 0
        do{
            option = view.menu()
            when(option){
                //1 -> runPlaylistMenu()
                //2 -> runSongMenu()
                3 -> artistController.run(artistMemStore)
                -1 -> return
            }
        } while (option != -1)
    }
}