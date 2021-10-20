package org.mk.playlist.app.views.console

class MainView {
    // Pass array lists used in main so that the menu can modify them
    fun menu() : Int {
        var option: Int = 0
        val input: String?
        // The main menu allows the user to go to one of the submenus
        println("\nMAIN MENU")
        println(" 1. Playlists")
        println(" 2. Songs")
        println(" 3. Artists")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
                input.toInt()
            else
                -9
        return option
    }
}
