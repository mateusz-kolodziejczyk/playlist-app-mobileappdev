package org.mk.playlist.app.models.song
import mu.KotlinLogging
import org.mk.playlist.app.utilities.logAll

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
        logAll(songs.values, logger)
    }
    override fun findAll() : ArrayList<Song>{
        return ArrayList(songs.values)
    }
    fun loadDummyData(){
        add(Song(title = "Call me maybe"))
        add(Song(title = "Bohemian Rhapsody"))
    }
}