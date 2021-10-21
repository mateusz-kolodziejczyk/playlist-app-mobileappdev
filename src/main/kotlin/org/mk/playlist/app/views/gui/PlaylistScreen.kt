package org.mk.playlist.app.views.gui

import javafx.application.Platform
import javafx.geometry.Orientation
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.utilities.songIDsToSongs
import tornadofx.*

class PlaylistScreen : View("Playlists") {
    private val playlistController: PlaylistController by inject()
    private val tableContent = playlistController.playlists.findAll()
    private val data = tableContent.asObservable()

    private lateinit var playlistTable: TableView<Playlist>

    override val root = vbox {
        gridpane{
            button ("Add Playlist")
            {
                action {
                   // println(playlistController.playlists.deleteSongFromOne(playlistTable.selectionModel.selectedItem.id))
                    replaceWith(AddPlaylistScreen::class, sizeToScene = true, centerOnScreen = true)
                }
            }
        }
        setPrefSize(800.0, 400.0)
        playlistTable = tableview(data) {
            column("ID", Playlist::id)
            column("Name", Playlist::name)
            rowExpander (expandOnDoubleClick = true) {
                // This uses a function to turn the song IDs stored in the playlist into song objects that are then
                // Displayed in a table.
                tableview(songIDsToSongs(it.songs.toList(), playlistController.songs).asObservable()){
                    column("ID", Song::id)
                    column("Title", Song::title)
                    column("Year", Song::year)
                    column("Artist ID", Song::artistId)
                }
            }
            println("Table created!")
        }
        button ("Refresh Table")
        {
            action {
                data.setAll(playlistController.playlists.findAll().asObservable())
            }
        }
        button("Quit to Main Menu") {
            useMaxWidth = true
            action{
                replaceWith(MainMenuScreen::class, sizeToScene = true, centerOnScreen = true)
            }
        }
    }
}
