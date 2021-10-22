package org.mk.playlist.app.views.gui.playlist

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.PlaylistController
import tornadofx.*

class AddPlaylistScreen : Fragment("Add Playlist") {
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
                    playlistController.add(_name.value)
                    close()
                }
            }
            button("Close"){
                useMaxWidth = true
                isDefaultButton = true
                action{
                    close()
                }
            }
        }

    }
}
