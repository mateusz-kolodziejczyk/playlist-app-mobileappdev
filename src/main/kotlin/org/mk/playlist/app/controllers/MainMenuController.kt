package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistMemStore
import org.mk.playlist.app.models.song.SongMemStore
import org.mk.playlist.app.views.console.MainView

class MainMenuController {
    var view = MainView()
    var artistMemStore = ArtistMemStore()
    var artistController = ArtistController()
    var songMemStore = SongMemStore()
    var songController = SongController()
    fun run(){
        var option = 0
        do{
            option = view.menu()
            when(option){
                //1 -> runPlaylistMenu()
                2 -> songController.run(artistMemStore, songMemStore)
                3 -> artistController.run(artistMemStore)
                -99 -> loadDummyData()
                -1 -> return
            }
        } while (option != -1)
    }
    fun loadDummyData(){
        artistMemStore.loadDummyData()
    }
}