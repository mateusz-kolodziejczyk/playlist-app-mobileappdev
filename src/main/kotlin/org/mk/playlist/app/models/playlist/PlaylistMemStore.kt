package org.mk.playlist.app.models.playlist

import mu.KotlinLogging
import org.mk.playlist.app.models.song.Song

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class PlaylistMemStore : PlaylistStore {
    var playlists: HashMap<Long, Playlist> = HashMap()
    override fun add(playlist: Playlist){

    }
    override fun addToPlaylist(id: Long, song: Song){

    }
    override fun findAll() : MutableList<Playlist>{
        return ArrayList()
    }
}