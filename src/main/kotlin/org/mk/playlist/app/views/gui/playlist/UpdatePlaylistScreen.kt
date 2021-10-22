package org.mk.playlist.app.views.gui.playlist

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.*

class UpdatePlaylistScreen : Fragment("Update Playlist")
{
    private val model = ViewModel()
    private val playlistController: PlaylistController by inject()
    private val _name = model.bind { SimpleStringProperty() }
    var playlistToUpdate: Playlist? = null

    init {
        playlistToUpdate = params["playlistToUpdate"] as? Playlist
        playlistToUpdate?.let {
            _name.value = it.name
        }
    }
    override val root = form {
        setPrefSize(800.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                        playlistToUpdate?.let {
                            playlistController.update(Playlist(id = it.id, name = _name.value, songs = it.songs ))
                        }
                        close()
                }
            }
            button("Close") {
                useMaxWidth = true
                isDefaultButton = true
                action {
                    close()
                }
            }
        }
    }
}
