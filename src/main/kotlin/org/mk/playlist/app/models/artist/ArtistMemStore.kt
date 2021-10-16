package org.mk.playlist.app.models.artist
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class ArtistMemStore : ArtistStore {
    // Use a map with K,V ID, Artist to make retrieval easier.
    val artists : MutableMap<Long,Artist> = mutableMapOf()
    override fun add(artist: Artist){
        artist.id = getId()
        artists[artist.id] = artist
    }
    override fun findAll() : ArrayList<Artist>{
        return ArrayList(artists.values)
    }
    fun logAll() {
        artists.values.forEach { logger.info("\n$it") }
    }
}