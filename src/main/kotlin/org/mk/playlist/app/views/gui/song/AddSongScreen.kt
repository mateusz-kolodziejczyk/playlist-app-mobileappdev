package org.mk.playlist.app.views.gui.song

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.ComboBox
import org.mk.playlist.app.controllers.gui.ArtistController
import org.mk.playlist.app.controllers.gui.SongController
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.models.song.Song
import tornadofx.*

class AddSongScreen : Fragment("Add Song") {
    private val model = ViewModel()
    private val songController : SongController by inject()
    private val artistController: ArtistController by inject()
    private val _title = model.bind { SimpleStringProperty() }
    private val _year = model.bind { SimpleStringProperty() }
    private val _artists = artistController.artists.findAll().asObservable()

    private var artistDropdown: ComboBox<Artist> by singleAssign()

    override val root = form {
        setPrefSize(800.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Title") {
                textfield(_title).required()
            }
            field("Year"){
                textfield(_year).required()
            }
            field("Artist"){
                artistDropdown = combobox<Artist>{
                    items = _artists
                }

            }
            button("Add"){
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action{
                    // Only let user add a song if an artis is chosen
                    artistDropdown.selectedItem?.let {
                        songController.add(_title.value, _year.value, it.id)
                        close()
                    }
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
