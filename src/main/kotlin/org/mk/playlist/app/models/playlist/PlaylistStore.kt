package org.mk.playlist.app.models.playlist

import org.mk.playlist.app.models.song.Song

interface PlaylistStore {
    fun add(playlist: Playlist)
    fun addToPlaylist(id: Long, song: Song) : Boolean
    fun findAll() : List<Playlist>
    fun findOne(id: Long) : Playlist?
    fun deleteOne(id: Long) : Boolean
    fun deleteSongFromAll(id: Long)
    fun deleteSongFromOne(id: Long, songID: Long)
    fun update(playlist: Playlist)
    fun findAllWithSong(songID: Long) : List<Playlist>
}