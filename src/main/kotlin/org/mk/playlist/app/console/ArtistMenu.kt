package org.mk.playlist.app.console

fun runArtistMenu(){
    var option : Int = 0
    var input: String?
    do{
        // The main menu allows the user to go to one of the submenus
        println("MAIN MENU")
        println(" 1. Add Artist")
        println(" 2. List all Artists")
        println("-1. Return to Main Menu")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        artistMenuOptions((option))
    }while(option != -1)

}

private fun artistMenuOptions(option: Int){
    when(option){
        1 -> runPlaylistMenu()
        2 -> runSongMenu()
    }
}
// Returns true if added artist, false if didn't
private fun addArtist() : Boolean{
    return true
}