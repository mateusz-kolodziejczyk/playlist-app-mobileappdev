package org.mk.playlist.app.views.gui.song

import javafx.scene.control.TableView
import org.mk.playlist.app.controllers.gui.SongController
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.views.gui.main.MainMenuScreen
import org.mk.playlist.app.views.gui.playlist.UpdatePlaylistScreen
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
                        find<AddSongScreen>().openWindow()
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
                        songTable.selectionModel.selectedItem?.let{
                            find<UpdateSongScreen>(mapOf(UpdateSongScreen::songToUpdate to it)).openWindow()
                        }
                    }
                }

            }
        }
        setPrefSize(800.0, 400.0)
        songTable = tableview(data) {
            readonlyColumn("ID", Song::id)
            readonlyColumn("Title", Song::title)
            readonlyColumn("Year", Song::year)
            readonlyColumn("Artist ID", Song::artistId)
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

