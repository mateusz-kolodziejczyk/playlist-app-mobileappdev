package org.mk.playlist.app.views.gui

import org.mk.playlist.app.controllers.gui.ArtistController
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.*

class ArtistScreen : View("My View") {
    private val artistController: ArtistController by inject()
    private val tableContent = artistController.artists.findAll()
    private val data = tableContent.asObservable()

    override val root = vbox {
        gridpane{
            button ("Cool button") {  }
        }
        setPrefSize(800.0, 400.0)
        tableview(data) {
            readonlyColumn("ID", Artist::id)
            readonlyColumn("First Name", Artist::firstName)
            readonlyColumn("Last Name", Artist::lastName)
        }
        button ("Refresh Table")
        {
            action {
                data.setAll(artistController.artists.findAll().asObservable())
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
