package org.mk.playlist.app.models.artist

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class ArtistStoreTest {

    private var artistMemStore:ArtistStore = ArtistMemStore()
    private val artistsToInit = mutableListOf(Artist(firstName = "Lady", lastName = "Gaga"), Artist(firstName = "Michael", lastName = "Jackson"))
    private val indexToGeneratedID = HashMap<Int, Long>()
    private val artistToAdd = Artist(id = 0L, firstName = "Carly", lastName = "Rae Jepsen")
    private val newFirstName = "New"
    private val newLastName = "Name"
    @BeforeEach
    fun setUp() {
        for (i in 0 until artistsToInit.size){
            indexToGeneratedID[i] = artistMemStore.add(artistsToInit[i])
        }
    }

    @AfterEach
    fun tearDown() {
        artistMemStore = ArtistMemStore()
    }

    @Test
    fun add() {
        val id = artistMemStore.add(artistToAdd)
        val foundArtist = artistMemStore.findOne(id)
        assertNotNull(foundArtist)
        foundArtist?.let {
            assertEquals(it.firstName, artistToAdd.firstName)
        }
    }

    @Test
    fun isEmpty() {
        assertFalse(artistMemStore.isEmpty())
        indexToGeneratedID.values.forEach{ artistMemStore.deleteOne(it)}
        assertTrue(artistMemStore.isEmpty())
    }

    @Test
    fun findAll() {
        val returnedArtists = artistMemStore.findAll()
        assertEquals(returnedArtists, artistsToInit)
    }

    @Test
    fun findOne() {
        val id = indexToGeneratedID[0]
        val returnedArtist = artistMemStore.findOne(id!!)
        assertNotNull(returnedArtist)
        assertEquals(returnedArtist, artistsToInit[0])
    }

    @Test
    fun deleteOne() {
        val id = indexToGeneratedID[0]
        assertNotNull(artistMemStore.findOne(id!!))
        artistMemStore.deleteOne(id)
        assertNull(artistMemStore.findOne(id))
    }

    @Test
    fun update() {
        val id = indexToGeneratedID[0]
        artistMemStore.update(Artist(id = id!!, firstName = newFirstName, lastName = newLastName))
        val foundArtist = artistMemStore.findOne(id)
        assertNotNull(foundArtist)
        foundArtist?.let {
            assertEquals(it.firstName, newFirstName)
            assertEquals(it.lastName, newLastName)
        }
    }
}