package org.mk.playlist.app.views.gui.artist

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.ArtistController
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.playlist.Playlist
import tornadofx.*

class UpdateArtistScreen : Fragment("Update Artist") {
    private val model = ViewModel()
    private val artistController: ArtistController by inject()
    private val _firstName = model.bind { SimpleStringProperty() }
    private val _lastName = model.bind { SimpleStringProperty() }
    var artistToUpdate:Artist? = null

    init {
        artistToUpdate = params["artistToUpdate"] as? Artist
        artistToUpdate?.let {
            _firstName.value = it.firstName
            _lastName.value = it.lastName
        }
    }

    override val root = form {
        setPrefSize(800.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("First Name") {
                textfield(_firstName).required()
            }
            field("Last Name") {
                textfield(_lastName).required()
            }
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    artistToUpdate?.let {
                        artistController.update(Artist(id = it.id, firstName = _firstName.value, lastName = _lastName.value))
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
