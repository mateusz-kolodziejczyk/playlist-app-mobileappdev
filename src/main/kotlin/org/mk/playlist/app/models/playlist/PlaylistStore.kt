package org.mk.playlist.app.models.playlist

import org.mk.playlist.app.models.song.Song

interface PlaylistStore {
    fun add(playlist: Playlist)
    fun addToPlaylist(id: Long, song: Song)
    fun findAll() : MutableList<Playlist>
}