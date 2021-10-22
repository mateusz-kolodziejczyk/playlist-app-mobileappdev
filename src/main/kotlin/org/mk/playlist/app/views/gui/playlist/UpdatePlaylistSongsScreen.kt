package org.mk.playlist.app.views.gui.playlist

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import javafx.scene.control.TableView
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.controllers.gui.SongController
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.utilities.songIDsToSongs
import org.mk.playlist.app.views.console.SongPlaylist
import org.mk.playlist.app.views.gui.main.MainMenuScreen
import tornadofx.*

class UpdatePlaylistSongsScreen : Fragment("Update Playlist Data") {
    private val playlistController: PlaylistController by inject()
    private val songController: SongController by inject()
    var playlistToUpdate: Playlist? = null

    private var playlistSongsTable: TableView<Song> by singleAssign()
    private val playlistSongData = ArrayList<Song>().asObservable()
    private val songsNotInPlaylistData = ArrayList<Song>().asObservable()
    private var songsNotInPlaylistTable: TableView<Song> by singleAssign()

    init {
        playlistToUpdate = params["playlistToUpdate"] as? Playlist
        // As a precaution, close the window if the playlist is null
        playlistToUpdate?.let { playlist ->
            // Set all the data in the first table by only inserting songs that the playlist contains
            playlistSongData.setAll(songIDsToSongs(playlist.songs.toList(), songController.songs))
            // Filter out the songs by asking whether the playlist contains it, if it does do not add to the table.
            songsNotInPlaylistData.setAll(songController.songs.filter { !playlist.songs.contains(it.id) })
        } ?: run {
            // Close window if the playlist is null
            close()
        }
    }

    override val root = vbox {
        setPrefSize(800.0, 400.0)
        gridpane {
            row {
                button("Add Song")
                {
                    action {
                        // Only run if a song was selected from the table of songs not in the playlist
                        songsNotInPlaylistTable.selectionModel.selectedItem?.let {
                            // playlist to update will not be null here, so I can use !!
                            playlistController.playlists.addToPlaylist(playlistToUpdate!!.id, it)
                            // Update the table data for both tables
                            songsNotInPlaylistData.remove(it)
                            playlistSongData.add(it)
                        }
                    }
                }
                button("Delete Song")
                {
                    action {
                        action {
                            // Only run if a song was selected from the table of songs in the playlist
                            playlistSongsTable.selectionModel.selectedItem?.let {
                                // playlist to update will not be null here, so I can use !!
                                playlistController.playlists.deleteSongFromOne(playlistToUpdate!!.id, it.id)
                                // Update the table data for both tables
                                playlistSongData.remove(it)
                                songsNotInPlaylistData.add(it)
                            }
                        }
                    }
                }
            }
            row {
                label(text = "Songs in Playlist")
                label(text = "Songs not in Playlist")
            }
            row {
                playlistSongsTable = tableview(playlistSongData) {
                    readonlyColumn("ID", Song::id)
                    readonlyColumn("Title", Song::title)
                    readonlyColumn("Year", Song::year)
                    readonlyColumn("Artist ID", Song::artistId)
                }
                songsNotInPlaylistTable = tableview(songsNotInPlaylistData) {
                    readonlyColumn("ID", Song::id)
                    readonlyColumn("Title", Song::title)
                    readonlyColumn("Year", Song::year)
                    readonlyColumn("Artist ID", Song::artistId)
                }
            }
            row {
                button("Close") {
                    useMaxWidth = true
                    action {
                        close()
                    }
                }
            }
        }
    }
}
