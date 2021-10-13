package org.mk.playlist.app.controllers

import org.mk.playlist.app.views.console.MainView

class MainMenuController {
    var view = MainView()
    var artistController = ArtistController()
    fun run(){
        var option = view.menu()
        when(option){
            //1 -> runPlaylistMenu()
            //2 -> runSongMenu()
            3 -> artistController.run()
        }
    }
}