package org.mk.playlist.app.models.song

interface SongStore {
    fun add(song : Song)
    fun findAll() : MutableList<Song>
}