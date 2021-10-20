package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.ArtistMemStore
import org.mk.playlist.app.models.playlist.PlaylistMemStore
import org.mk.playlist.app.models.song.SongMemStore
import org.mk.playlist.app.views.console.MainView

class MainMenuController {
    var view = MainView()
    var artistMemStore = ArtistMemStore()
    var artistController = ArtistController()
    var songMemStore = SongMemStore()
    var songController = SongController()
    var playlistMemStore = PlaylistMemStore()
    var playlistController = PlaylistController()
    fun run(){
        var option = 0
        do{
            option = view.menu()
            when(option){
                1 -> playlistController.run(songMemStore, playlistMemStore)
                2 -> songController.run(artistMemStore, songMemStore, playlistMemStore)
                3 -> artistController.run(artistMemStore, songMemStore, playlistMemStore)
                -99 -> loadDummyData()
                -1 -> return
            }
        } while (option != -1)
    }
    fun loadDummyData(){
        artistMemStore.loadDummyData()
        songMemStore.loadDummyData()
        playlistMemStore.loadDummyData()
    }
}