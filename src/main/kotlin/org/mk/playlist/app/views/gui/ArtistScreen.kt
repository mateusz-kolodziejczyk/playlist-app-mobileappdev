package org.mk.playlist.app.views.gui

import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.*

class ArtistScreen : View("My View") {
    private val playlistController: PlaylistController by inject()
    private val tableContent = playlistController.playlists.findAll()
    private val data = tableContent.asObservable()

    override val root = vbox {
        gridpane{
            button ("Cool button") {  }
        }
        setPrefSize(800.0, 400.0)
        tableview(data) {
            readonlyColumn("ID", Playlist::id)
            readonlyColumn("Name", Playlist::name)
        }
        button("Quit to Main Menu") {
            useMaxWidth = true
            action {
                replaceWith(MainMenuScreen::class, sizeToScene = true, centerOnScreen = true)
            }
        }
    }
}
