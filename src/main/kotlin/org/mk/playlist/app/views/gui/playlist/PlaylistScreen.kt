package org.mk.playlist.app.views.gui.playlist

import javafx.collections.MapChangeListener
import javafx.scene.control.TableView
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.utilities.songIDsToSongs
import org.mk.playlist.app.views.gui.main.MainMenuScreen
import tornadofx.*

class PlaylistScreen : View("Playlists") {
    private val playlistController: PlaylistController by inject()
    private val tableContent = playlistController.playlists.findAll()
    private val data = tableContent.asObservable()

    private var playlistTable: TableView<Playlist> by singleAssign()



    override val root = vbox {
        gridpane {
            row {
                button("Add Playlist")
                {
                    action {
                        find<AddPlaylistScreen>().openWindow()
                    }
                }
                button("Delete Selected Playlist")
                {
                    action {
                        playlistTable.selectionModel.selectedItem?.let {
                            playlistController.deleteOne(it.id)
                        }
                    }
                }
                button("Update Playlist Name") {
                    action{
                        playlistTable.selectionModel.selectedItem?.let {
                            find<UpdatePlaylistScreen>(mapOf(UpdatePlaylistScreen::playlistToUpdate to it)).openWindow()
                        }
                    }
                }
                button("Update Playlist Songs"){
                    action {
                        playlistTable.selectionModel.selectedItem?.let{
                            find<UpdatePlaylistSongsScreen>(mapOf(UpdatePlaylistSongsScreen::playlistToUpdate to it)).openWindow()
                        }
                    }
                }
            }

        }
        setPrefSize(800.0, 400.0)
        playlistTable = tableview(data) {
            column("ID", Playlist::id)
            column("Name", Playlist::name)
            rowExpander(expandOnDoubleClick = true) {
                // This uses a function to turn the song IDs stored in the playlist into song objects that are then
                // Displayed in a table.
                tableview(songIDsToSongs(it.songs.toList(), playlistController.songs).asObservable()) {
                    column("ID", Song::id)
                    column("Title", Song::title)
                    column("Year", Song::year)
                    column("Artist ID", Song::artistId)
                }
            }
            println("Table created!")
        }
        button("Refresh Table")
        {
            action {
                refreshTable()
            }
        }
        button("Quit to Main Menu") {
            useMaxWidth = true
            action {
                replaceWith(MainMenuScreen::class, sizeToScene = true, centerOnScreen = true)
            }
        }
    }

    init {
        // Add a listener that will update the table whenever a playlist is added/deleted
        // It does not fire when a playlist is updated, as it only triggers when the map changes.
        playlistController.playlists.playlists.addListener(MapChangeListener {
            data.setAll(playlistController.playlists.findAll().asObservable())
            println("Did Something!")
        })
    }

    private fun refreshTable() {
        data.setAll(playlistController.playlists.findAll().asObservable())
    }
}
