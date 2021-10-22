package org.mk.playlist.app.models.song

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mk.playlist.app.models.artist.Artist

internal class SongStoreTest {

    private var songMemStore:SongStore = SongMemStore()
    private val songsToInit = mutableListOf(Song(title = "Call Me Maybe", year = "2012", artistId = 0L), Song(title = "Run Away With Me", year = "2015", artistId = 0L))
    private val indexToGeneratedID = HashMap<Int, Long>()
    private val songToAdd = Song(title = "Thriller", year = "1982", artistId = 2L)
    private val newTitle = "Poker Face"
    private val newYear = "2009"
    private val newArtistID = 1L
    private val artistIDToDelete = 0L

    @BeforeEach
    fun setUp() {
        for (i in 0 until songsToInit.size){
            indexToGeneratedID[i] = songMemStore.add(songsToInit[i])
        }
    }

    @AfterEach
    fun tearDown() {
        songMemStore = SongMemStore()
    }
    @Test
    fun add() {
        val id = songMemStore.add(songToAdd)
        assertNotNull(songMemStore.findOne(id))
        assertEquals(songMemStore.findOne(id), songToAdd)
    }
    @Test
    fun findAll() {
        val returnedSongs = songMemStore.findAll()
        assertEquals(returnedSongs, songsToInit)
    }

    @Test
    fun findOne() {
        val id = indexToGeneratedID[0]
        val returnedSong = songMemStore.findOne(id!!)
        assertNotNull(returnedSong)
        assertEquals(returnedSong, songsToInit[0])
    }

    @Test
    fun deleteOne() {
        val id = indexToGeneratedID[0]
        assertNotNull(songMemStore.findOne(id!!))
        songMemStore.deleteOne(id)
        assertNull(songMemStore.findOne(id))
    }

    @Test
    fun update() {
        val id = indexToGeneratedID[0]
        songMemStore.update(Song(id = id!!, title = newTitle, year = newYear, artistId = newArtistID))
        val foundSong = songMemStore.findOne(id)
        assertNotNull(foundSong)
        foundSong?.let {
            assertEquals(it.title, newTitle)
            assertEquals(it.year, newYear)
            assertEquals(it.artistId, newArtistID)
        }
    }
}