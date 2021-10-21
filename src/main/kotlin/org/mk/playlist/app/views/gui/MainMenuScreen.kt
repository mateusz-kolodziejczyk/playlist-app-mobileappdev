package org.mk.playlist.app.views.gui

import javafx.application.Platform
import javafx.geometry.Orientation
import org.mk.playlist.app.controllers.gui.PlaylistController
import tornadofx.*

class MainMenuScreen : View("Playlist App") {


    override val root = form {
        setPrefSize(800.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Playlists") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    replaceWith(PlaylistScreen::class, sizeToScene = true, centerOnScreen = true)
                }
            }

            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}
