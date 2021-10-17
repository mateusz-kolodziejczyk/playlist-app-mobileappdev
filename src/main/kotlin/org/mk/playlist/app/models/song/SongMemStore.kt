package org.mk.playlist.app.models.song
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class SongMemStore : SongStore {
    // Use a map with K,V ID, Artist to make retrieval easier.
    val songs : HashMap<Long,Song> = HashMap()

    override fun add(song: Song){
        song.id = getId()
        songs[song.id] = song
        logAll()
    }
    override fun findAll() : ArrayList<Song>{
        return ArrayList(songs.values)
    }
    fun logAll() {
        songs.values.forEach { logger.info("\n$it") }
    }
}