package org.mk.playlist.app.models.song

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.utilities.*

private val logger = KotlinLogging.logger {}

const val JSON_FILE = "json/songs.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.HashMap<Long, Song>>() {}.type

class SongJSONStore : SongStore {
    // Use a map with K,V ID, Artist to make retrieval easier.
    private var songs : HashMap<Long,Song> = HashMap()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun add(song: Song){
        song.id = generateRandomId()
        songs[song.id] = song
        logAll(songs.values, logger)
        serialize()
    }
    override fun findAll() : ArrayList<Song>{
        return ArrayList(songs.values)
    }
    override fun findOne(id: Long) : Song?{
        return songs[id]
    }

    override fun deleteOne(id: Long): Boolean {
        return if(songs.remove(id) == null){
            logger.info { "Could not remove song with id [$id]" }
            false
        }
        else{
            logger.info { "Successfully removed song with id [$id]" }
            serialize()
            true
        }
    }

    override fun deleteSongsByArtist(id: Long) {
        for(song in songs.values){
            if(song.artistId == id){
                songs.remove(song.id)
            }
        }
        serialize()
    }

    override fun filter(predicate: (Song) -> Boolean): List<Song> {
        return songs.values.filter(predicate)
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(songs, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        songs = Gson().fromJson(jsonString, listType)
    }
}