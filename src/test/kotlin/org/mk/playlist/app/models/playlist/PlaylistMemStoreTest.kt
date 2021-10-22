package org.mk.playlist.app.models.playlist

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mk.playlist.app.models.song.Song

// Only testing the memory store version as Json would save these playlists to files in testing.
// Memory store methods are all identical to json ones aside from the added file IO
internal class PlaylistMemStoreTest {

    private var playlistMemStore = PlaylistMemStore()
    private val playlistsToInit = mutableListOf<Playlist>(Playlist(id=0, name="Playlist One"), Playlist(id=1, name="Playlist Two"), Playlist(id=2, name="Playlist Three"))
    // Song to add
    val songToAdd = Song(id=0L, title = "New Song", year = "2021", artistId = 1L)
    // Song to remove
    // Playlist to add
    val playlistToAdd = Playlist(id = -10L, name = "New Playlist")
    val indextoGeneratedID = HashMap<Int, Long>()
    @BeforeEach
    fun setUp(){
        for (i in 0 until playlistsToInit.size){
            indextoGeneratedID[i] = playlistMemStore.add(playlistsToInit[i])
        }
    }
    @AfterEach
    fun tearDown(){
        playlistMemStore = PlaylistMemStore()
    }
    @Test
    fun add() {
        val newID = playlistMemStore.add(playlistToAdd)
        assertNotNull(playlistMemStore.findOne(newID))
    }

    @Test
    fun addToPlaylist() {
        val id = indextoGeneratedID[0]!!
        // Check if the song was added correctly.
        playlistMemStore.addToPlaylist(id, songToAdd)
        assertTrue(playlistMemStore.findOne(id)!!.songs.contains(songToAdd.id))
        // Check that the song was not duplicated
        playlistMemStore.addToPlaylist(id, songToAdd)
        assertTrue(playlistMemStore.findOne(id)?.songs?.size == 1)
    }

    @Test
    fun findAll() {
        val foundPlaylists = playlistMemStore.findAll()
        assertEquals(foundPlaylists, playlistsToInit)
    }

    @Test
    fun findOne() {
        val index = 0
        val foundPlaylist = playlistMemStore.findOne(indextoGeneratedID[index]!!)
        assertEquals(playlistsToInit[0], foundPlaylist)
    }

    @Test
    fun deleteOne() {
        val id = indextoGeneratedID[0]!!
        playlistMemStore.deleteOne(id)
        // If it's null, the playlist with that id no longer exists
        assertNull(playlistMemStore.findOne(id))
    }

    @Test
    fun deleteSongFromAll() {
        // Add song to two playlists.
        val firstID = indextoGeneratedID[0]!!
        val secondID = indextoGeneratedID[0]!!
        playlistMemStore.addToPlaylist(firstID, songToAdd)
        playlistMemStore.addToPlaylist(secondID, songToAdd)

        playlistMemStore.deleteSongFromAll(songToAdd.id)

        // Should be empty
        assertTrue(playlistMemStore.findAllWithSong(songToAdd.id).isEmpty())
    }

    @Test
    fun update() {
        val id = indextoGeneratedID[0]!!
        val newName = "New Name"
        playlistMemStore.update(Playlist(id = id, name = newName))
        assertEquals(newName, playlistMemStore.findOne(id)?.name)
    }

    @Test
    fun findAllWithSong() {
        val firstID = indextoGeneratedID[0]!!
        val secondID = indextoGeneratedID[1]!!
        assertTrue(playlistMemStore.findAllWithSong(songToAdd.id).isEmpty())

        playlistMemStore.addToPlaylist(firstID, songToAdd)
        assertTrue(playlistMemStore.findAllWithSong(songToAdd.id).size == 1)

        playlistMemStore.addToPlaylist(secondID, songToAdd)
        assertTrue(playlistMemStore.findAllWithSong(songToAdd.id).size == 2)
    }
}