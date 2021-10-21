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

    private var playlistTable: TableView<Playlist> by singleAssign()
    private lateinit var songTable: TableView<Song>

    override val root = vbox {
        gridpane{
            button ("Cool button")
            {
                action {
                    println(playlistTable.selectionModel.selectedItem)
                }
            }
        }
        setPrefSize(800.0, 400.0)
        playlistTable = tableview(data) {
            readonlyColumn("ID", Playlist::id)
            readonlyColumn("Name", Playlist::name)
            rowExpander (expandOnDoubleClick = true) {
                // This uses a function to turn the song IDs stored in the playlist into song objects that are then
                // Displayed in a table.
                songTable = tableview(songIDsToSongs(it.songs.toList(), playlistController.songs).asObservable()){
                    readonlyColumn("ID", Song::id)
                    readonlyColumn("Title", Song::title)
                    readonlyColumn("Year", Song::year)
                    readonlyColumn("Artist ID", Song::artistId)
                }
            }
        }
        button("Quit to Main Menu") {
            useMaxWidth = true
            action {
                replaceWith(MainMenuScreen::class, sizeToScene = true, centerOnScreen = true)
            }
        }
    }
}
