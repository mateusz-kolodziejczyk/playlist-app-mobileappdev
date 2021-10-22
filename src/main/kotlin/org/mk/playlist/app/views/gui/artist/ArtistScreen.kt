package org.mk.playlist.app.views.gui.artist

import javafx.scene.control.TableView
import org.mk.playlist.app.controllers.gui.ArtistController
import org.mk.playlist.app.controllers.gui.PlaylistController
import org.mk.playlist.app.controllers.gui.SongController
import org.mk.playlist.app.models.artist.Artist
import org.mk.playlist.app.views.gui.main.MainMenuScreen
import org.mk.playlist.app.views.gui.song.AddSongScreen
import tornadofx.*

class ArtistScreen : View("Artists") {
    private val artistController: ArtistController by inject()
    private val songController: SongController by inject()
    private val playlistController: PlaylistController by inject()
    private val tableContent = artistController.artists.findAll()
    private val data = tableContent.asObservable()
    private var artistTable : TableView<Artist> by singleAssign()
    override val root = vbox {
        gridpane{
            row{
                button ("Add Artist") {
                    action {
                        find<AddArtistScreen>().openWindow()
                    }
                }
                button ("Delete Selected Artist") {
                    action {
                        artistTable.selectionModel.selectedItem?.let { artist ->
                            val songsToDelete = songController.songs.filter { it.artistId == artist.id }
                            for(song in songsToDelete){
                                println("Deleted songfrom playlist $song")
                                playlistController.deleteSongFromPlaylists(song.id)
                            }
                            songController.deleteByArtist(artist.id)
                            artistController.deleteOne(artist.id)
                        }
                        refreshTable()
                    }
                }
                button("Update Selected Artist") {
                    action{
                            artistTable.selectionModel.selectedItem?.let {
                                find<UpdateArtistScreen>(mapOf(UpdateArtistScreen::artistToUpdate to it)).openWindow()
                            }
                    }
                }
            }
        }
        setPrefSize(800.0, 400.0)
        artistTable = tableview(data) {
            readonlyColumn("ID", Artist::id)
            readonlyColumn("First Name", Artist::firstName)
            readonlyColumn("Last Name", Artist::lastName)
        }
        button ("Refresh Table")
        {
            action {
                refreshTable()
            }
        }
        button("Quit to Main Menu") {
            useMaxWidth = true
            action {
                replaceWith(MainMenuScreen::class, sizeToScene = true, centerOnScreen = true)
            }
        }
    }
    private fun refreshTable() {
        data.setAll(artistController.artists.findAll().asObservable())
    }
}
