package org.mk.playlist.app.models.artist
import mu.KotlinLogging
import org.mk.playlist.app.utilities.logAll

private val logger = KotlinLogging.logger {}
var lastId = 0L

private fun getId(): Long {
    return lastId++
}
class ArtistMemStore : ArtistStore {
    // Use a map with K,V ID, Artist to make retrieval easier.
    val artists : HashMap<Long,Artist> = HashMap()
    override fun add(artist: Artist){
        artist.id = getId()
        artists[artist.id] = artist
        logAll(artists.values, logger)
    }

    override fun isEmpty() : Boolean{
        return artists.isEmpty()
    }
    override fun findAll() : ArrayList<Artist>{
        return ArrayList(artists.values)
    }

    override fun findOne(id: Long): Artist? {
        // Try to find the id in the hashmap, if it's not there catch the exception and return null
        return try {
            artists[id]
        } catch(exception: Exception){
            logger.info { "Exception $exception caught trying to find using id $id" }
            null
        }
    }

    override fun deleteOne(id: Long) : Boolean{
        return if(artists.remove(id) == null){
            logger.info { "Could not remove artist with id [$id]" }
            false
        }
        else{
            logger.info { "Successfully removed artist with id [$id]" }
            true
        }
    }

    override fun update(artist: Artist){
        val foundArtist = artists[artist.id]
        if(foundArtist != null){
            foundArtist.firstName = artist.firstName
            foundArtist.lastName = artist.lastName
        }
    }
    fun loadDummyData(){
        add(Artist(firstName = "Carly", lastName = "Rae Jepsen"))
        add(Artist(firstName = "Taylor", lastName = "Swift"))
    }
}