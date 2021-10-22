package org.mk.playlist.app.views.gui

import javafx.scene.control.TableView
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.controllers.gui.SongController
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song
import tornadofx.*

class SongScreen : View("Songs") {
    private val songController: SongController by inject()
    private val tableContent = songController.songs.findAll()
    private val data = tableContent.asObservable()
    private var songTable : TableView<Song> by singleAssign()

    override val root = vbox {
        gridpane{
            row{
                button ("Add Song") {
                    action{
                        find(AddSongScreen::class).currentSong = songTable.selectionModel.selectedItem
                        replaceWith(AddSongScreen::class, sizeToScene = true, centerOnScreen = true)
                    }
                }
                button ("Delete Song") {
                    action{
                        songTable.selectionModel.selectedItem?.let {
                            songController.deleteOne(it.id)
                        }
                        refreshTable()
                    }
                }
                button("Update Selected Song") {
                    action{

                    }
                }

            }
        }
        setPrefSize(800.0, 400.0)
        songTable = tableview(data) {
            readonlyColumn("ID", Song::id)
            readonlyColumn("Title", Song::title)
            readonlyColumn("Year", Song::year)
        }
        button ("Refresh Table")
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
    private fun refreshTable() {
        data.setAll(songController.songs.findAll().asObservable())
    }
}
