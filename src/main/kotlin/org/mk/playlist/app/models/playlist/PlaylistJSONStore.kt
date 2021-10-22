package org.mk.playlist.app.models.playlist

import org.mk.playlist.app.models.song.Song
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import javafx.collections.ObservableMap
import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.utilities.*
import tornadofx.*

private val logger = KotlinLogging.logger {}

const val JSON_FILE = "json/playlists.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.HashMap<Long, Playlist>>() {}.type

class PlaylistJSONStore : PlaylistStore {
    var playlists by property<ObservableMap<Long, Playlist>>()
    val playlistsProperty = mapProperty(playlists)

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

    override fun findAll(): List<Playlist> {
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
    override fun update(playlist: Playlist){
        val foundPlaylist = playlists[playlist.id]
        if(foundPlaylist != null){
            foundPlaylist.name = playlist.name
        }
        serialize()
    }

    override fun findAllWithSong(songID: Long): List<Playlist> {
        // Simple filter to find all playlists containing a particular song
        return playlists.values.filter { playlist -> playlist.songs.contains(songID) }
    }
    // Serialize and deserialize are changed to fit having playlists stored as a property, now have to cast them
    // into hashmaps to serialize/deserialize them from disk.
    private fun serialize() {
        val playlistHashMap = HashMap<Long, Playlist>()
        playlists.keys.forEach{key -> playlistHashMap[key] = playlists[key]!!}
        val jsonString = gsonBuilder.toJson(playlists, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        var playlistsHashMap : HashMap<Long, Playlist> = Gson().fromJson(jsonString, listType)
        playlists = playlistsHashMap.asObservable()
    }
}