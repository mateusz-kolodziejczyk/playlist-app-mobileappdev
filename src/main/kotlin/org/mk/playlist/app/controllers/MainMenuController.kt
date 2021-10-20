package org.mk.playlist.app.controllers

import org.mk.playlist.app.models.artist.ArtistJSONStore
import org.mk.playlist.app.models.artist.ArtistMemStore
import org.mk.playlist.app.models.playlist.PlaylistJSONStore
import org.mk.playlist.app.models.playlist.PlaylistMemStore
import org.mk.playlist.app.models.song.SongJSONStore
import org.mk.playlist.app.models.song.SongMemStore
import org.mk.playlist.app.views.console.MainView

class MainMenuController {
    private var view = MainView()
    // Memory Stores
    private var artistMemStore = ArtistMemStore()
    private var songMemStore = SongMemStore()
    private var playlistMemStore = PlaylistMemStore()
    // JSON Stores
    private var artistJSONStore = ArtistJSONStore()
    private var songJSONStore = SongJSONStore()
    private var playlistJSONStore = PlaylistJSONStore()
    // Controllers
    private var artistController = ArtistController()
    private var songController = SongController()
    private var playlistController = PlaylistController()

    fun run(){
        var option = 0
        do{
            option = view.menu()
            when(option){
                1 -> playlistController.run(songJSONStore, playlistJSONStore)
                2 -> songController.run(artistJSONStore, songJSONStore, playlistJSONStore)
                3 -> artistController.run(artistJSONStore, songJSONStore, playlistJSONStore)
                -99 -> loadDummyData()
                -1 -> return
            }
        } while (option != -1)
    }
    private fun loadDummyData(){
        artistMemStore.loadDummyData()
        songMemStore.loadDummyData()
        playlistMemStore.loadDummyData()
    }
}