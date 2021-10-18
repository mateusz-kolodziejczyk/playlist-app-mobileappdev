package org.mk.playlist.app.models.playlist

import mu.KotlinLogging
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.utilities.logAll

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class PlaylistMemStore : PlaylistStore {
    var playlists: HashMap<Long, Playlist> = HashMap()
    override fun add(playlist: Playlist){
        playlist.id = getId()
        playlists[playlist.id] = playlist
        logAll(playlists.values, logger)
    }
    override fun addToPlaylist(id: Long, song: Song){

    }
    override fun findAll() : MutableList<Playlist>{
        return ArrayList(playlists.values)
    }

    fun loadDummyData(){
        add(Playlist(name = "Favorite songs"))
        add(Playlist(name = "Greatest Rock Songs"))
    }
}