package org.mk.playlist.app.models.playlist

import org.mk.playlist.app.models.song.Song
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.utilities.*

private val logger = KotlinLogging.logger {}

const val JSON_FILE = "json/playlists.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.HashMap<Long, Playlist>>() {}.type

class PlaylistJSONStore : PlaylistStore {
    private var playlists: HashMap<Long, Playlist> = HashMap()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun add(playlist: Playlist) {
        playlist.id = generateRandomId()
        playlists[playlist.id] = playlist
        logAll(playlists.values, logger)
        serialize()
    }

    override fun addToPlaylist(id: Long, song: Song): Boolean {
        val playlist = playlists[id]
        return if (playlist != null) {
            // If playlist does not already contain the song, add it.
            if (playlist.songs.add(song.id)) {
                logger.info { "$song added to playlist [$id]" }
                serialize()
                true
            } else {
                logger.info { "Trying to add duplicate song to playlist." }
                false
            }
        } else {
            logger.info { "Playlist does not exist." }
            false
        }
    }

    override fun findAll(): MutableList<Playlist> {
        return ArrayList(playlists.values)
    }

    override fun findOne(id: Long): Playlist? {
        return playlists[id]
    }

    override fun deleteOne(id: Long): Boolean {
        return if (playlists.remove(id) == null) {
            logger.info { "Failed to remove playlist with id [$id]" }
            false
        } else {
            logger.info { "Successfully removed playlist with id [$id]" }
            serialize()
            true
        }
    }

    // This function works alongside the remove song function in SongStore to remove any instance of a song
    // that was removed
    override fun deleteSongFromAll(id: Long) {
        for(playlist in playlists.values){
            playlist.songs.remove(id)
        }
        serialize()
    }

    override fun deleteSongFromOne(id: Long, songID: Long) {
        val playlist = playlists[id]
        if(playlist != null){
            if(playlist.songs.remove(songID)){
                logger.info { "Successfully deleted song with id [$songID] from playlist [$id]" }
            }
            else{
                logger.info { "Could not find song with id [$songID] in playlist [$id]"}
            }
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(playlists, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        playlists = Gson().fromJson(jsonString, listType)
    }
}