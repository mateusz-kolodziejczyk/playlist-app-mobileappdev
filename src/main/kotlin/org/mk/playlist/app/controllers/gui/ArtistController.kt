package org.mk.playlist.app.controllers.gui

import mu.KotlinLogging
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.artist.ArtistJSONStore
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.playlist.PlaylistJSONStore
import org.mk.playlist.app.models.song.SongJSONStore
import org.mk.playlist.app.utilities.listAllArtists
import tornadofx.Controller

class ArtistController : Controller() {
    val logger = KotlinLogging.logger {}
    val artists = ArtistJSONStore()
    val songs = SongJSONStore()
    val playlists = PlaylistJSONStore()
    fun add(firstName: String, lastName: String) {
        artists.add(Artist(firstName = firstName, lastName = lastName))
    }

    fun deleteOne(id: Long) {
        artists.deleteOne(id)
    }

    fun update(artist: Artist){
        artists.update(artist)
    }
}
