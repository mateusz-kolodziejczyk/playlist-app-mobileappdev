package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.ArtistJSONStore
import org.mk.playlist.app.models.playlist.PlaylistJSONStore
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongJSONStore
import tornadofx.Controller

class SongController : Controller() {
    val logger = KotlinLogging.logger{}
    val songs = SongJSONStore()
    val artists = ArtistJSONStore()
    val playlists = PlaylistJSONStore()
    fun add(title: String, year: String, artistID: Long){
        songs.add(Song(title = title, year = year, artistId = artistID))
    }

    // Delete song from all playlists, then remove the song itself.
    fun deleteOne(id: Long) {
        playlists.deleteSongFromAll(id)
        songs.deleteOne(id)
    }

    // Delete songs if they are by a particular artist
    fun deleteByArtist(artistID: Long){
        val songsToDelete = songs.filter { it.artistId == artistID }
        songsToDelete.forEach{songs.deleteOne(it.id)}
    }

    fun update(song: Song){
        songs.update(song)
    }
}