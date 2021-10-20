package org.mk.playlist.app.models.artist

interface ArtistStore {
    fun add(artist: Artist)
    fun findAll() : MutableList<Artist>
    fun isEmpty() : Boolean
    fun findOne(id: Long) : Artist?
    fun deleteOne(id: Long) : Boolean
}