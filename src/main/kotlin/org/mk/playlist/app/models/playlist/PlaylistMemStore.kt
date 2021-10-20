package org.mk.playlist.app.models.playlist

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.utilities.logAll

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlaylistMemStore : PlaylistStore {
    var playlists: HashMap<Long, Playlist> = HashMap()
    override fun add(playlist: Playlist) {
        playlist.id = getId()
        playlists[playlist.id] = playlist
        logAll(playlists.values, logger)
    }

    override fun addToPlaylist(id: Long, song: Song): Boolean {
        val playlist = playlists[id]
        return if (playlist != null) {
            // If playlist does not already contain the song, add it.
            if (playlist.songs.add(song.id)) {
                logger.info { "$song added to playlist [$id]" }
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
            true
        }
    }

    // This function works alongside the remove song function in SongStore to remove any instance of a song
    // that was removed
    override fun deleteSongFromAll(id: Long) {
        for(playlist in playlists.values){
            playlist.songs.remove(id)
        }
    }

    fun loadDummyData() {
        add(Playlist(name = "Favorite songs"))
        add(Playlist(name = "Greatest Rock Songs"))
    }
}