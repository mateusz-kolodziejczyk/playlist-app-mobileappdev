package org.mk.playlist.app.views.gui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.*

class AddPlaylistScreen : View("Add Playlist") {
    private val model = ViewModel()
    private val playlistController: PlaylistController by inject()
    private val _name = model.bind { SimpleStringProperty()}
    override val root = form {
        setPrefSize(800.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            button("Add"){
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action{
                    runAsyncWithProgress {
                        playlistController.add(_name.value)
                    }
                }
            }
            button("Close"){
                useMaxWidth = true
                isDefaultButton = true
                action{
                    replaceWith(PlaylistScreen::class, sizeToScene = true, centerOnScreen = true)
                }
            }
        }

    }
}
