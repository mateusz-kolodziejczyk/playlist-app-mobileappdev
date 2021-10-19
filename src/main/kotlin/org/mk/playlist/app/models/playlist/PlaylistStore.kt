package org.mk.playlist.app.models.playlist

import org.mk.playlist.app.models.song.Song

interface PlaylistStore {
    fun add(playlist: Playlist)
    fun addToPlaylist(id: Long, song: Song) : Boolean
    fun findAll() : MutableList<Playlist>
    fun findOne(id: Long) : Playlist?
}