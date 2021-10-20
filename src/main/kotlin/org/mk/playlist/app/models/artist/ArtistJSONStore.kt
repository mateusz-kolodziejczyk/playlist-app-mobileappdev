package org.mk.playlist.app.models.artist

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.mk.playlist.app.utilities.*

private val logger = KotlinLogging.logger {}

const val JSON_FILE = "json/artists.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.HashMap<Long, Artist>>() {}.type

class ArtistJSONStore : ArtistStore {
    // Use a map with K,V ID, Artist to make retrieval easier.
    private var artists : HashMap<Long,Artist> = HashMap()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun add(artist: Artist){
        artist.id = generateRandomId()
        artists[artist.id] = artist
        logAll(artists.values, logger)
        serialize()
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
            serialize()
            true
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(artists, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        artists = Gson().fromJson(jsonString, listType)
    }
}