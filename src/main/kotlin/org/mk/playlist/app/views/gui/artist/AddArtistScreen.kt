package org.mk.playlist.app.views.gui.artist

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.ArtistController
import tornadofx.*

class AddArtistScreen : View("Add Artist") {
    private val model = ViewModel()
    private val artistController: ArtistController by inject()
    private val _firstName = model.bind { SimpleStringProperty() }
    private val _lastName = model.bind { SimpleStringProperty() }

    override val root = form {
        setPrefSize(800.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("First Name") {
                textfield(_firstName).required()
            }
            field("Last Name") {
                textfield(_lastName).required()
            }
            button("Add"){
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action{
                    runAsyncWithProgress {
                        artistController.add(_firstName.value, _lastName.value)
                    }
                }
            }
            button("Close"){
                useMaxWidth = true
                isDefaultButton = true
                action{
                    replaceWith(ArtistScreen::class, sizeToScene = true, centerOnScreen = true)
                }
            }
        }

    }
}
