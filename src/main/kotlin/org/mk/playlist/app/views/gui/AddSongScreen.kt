package org.mk.playlist.app.views.gui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.models.song.Song
import tornadofx.*

class AddSongScreen : View("Add Song") {
    private val model = ViewModel()
    private val playlistController: PlaylistController by inject()
    var currentSong : Song? = null
    private val _name = model.bind { SimpleStringProperty() }
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
                    println(currentSong)
                    replaceWith(SongScreen::class, sizeToScene = true, centerOnScreen = true)
                }
            }
        }

    }
}
