package org.mk.playlist.app.views.gui

import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.controllers.gui.SongController
import org.mk.playlist.app.models.playlist.Playlist
import org.mk.playlist.app.models.song.Song
import tornadofx.*

class SongScreen : View("My View") {
    private val songController: SongController by inject()
    private val tableContent = songController.songs.findAll()
    private val data = tableContent.asObservable()

    override val root = vbox {
        gridpane{
            button ("Cool button") {  }
        }
        setPrefSize(800.0, 400.0)
        tableview(data) {
            readonlyColumn("ID", Song::id)
            readonlyColumn("Title", Song::title)
            readonlyColumn("Year", Song::year)
        }
        button ("Refresh Table")
        {
            action {
                data.setAll(songController.songs.findAll().asObservable())
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
