package org.mk.playlist.app.views.gui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.*

class UpdatePlaylistScreen : View("Update Playlist")
{private val model = ViewModel()
    private val playlistController: PlaylistController by inject()
    private val _name = model.bind { SimpleStringProperty() }
    private var playlistToUpdate: Playlist = Playlist()
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
                    runAsyncWithProgress {
                        playlistController.update(Playlist(id = playlistToUpdate.id, name = _name.value, songs = playlistToUpdate.songs ))
                        close()
                    }
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
    fun setPlaylistToUpdate(playlist: Playlist){
        playlistToUpdate = playlist
        _name.value = playlist.name
    }
}
