package org.mk.playlist.app.models.song

interface SongStore {
    fun add(song : Song)
    fun findAll() : MutableList<Song>
    fun findOne(id: Long) : Song?
    fun deleteOne(id: Long) : Boolean
    fun deleteSongsByArtist(id: Long)
    fun filter(predicate: (Song) -> Boolean) : List<Song>
    fun update(song: Song)
}