package org.mk.playlist.app.models.artist

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ArtistMemStoreTest {

    val artistMemStore = ArtistMemStore()
    val artistInitList = mutableListOf<Artist>(Artist(firstName = "Lady", lastName = "Gaga"), Artist(firstName = "Michael", lastName = "Jackson"))
    val artistToAdd = Artist(id = 0L, firstName = "Carly", lastName = "Rae Jepsen")
    val newFirstName = "New"
    val newLastName = "Name"
    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun add() {
    }

    @Test
    fun isEmpty() {
    }

    @Test
    fun findAll() {
    }

    @Test
    fun findOne() {
    }

    @Test
    fun deleteOne() {
    }

    @Test
    fun update() {
    }
}