package org.mk.playlist.app.console

fun runMainMenu(){
    var option : Int = 0
    var input: String?
    do{
        // The main menu allows the user to go to one of the submenus
        println("MAIN MENU")
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
        mainMenuOptions(option)
    }while(option != -1)
}

private fun mainMenuOptions(option: Int){
    when(option){

    }
}