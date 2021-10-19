package org.mk.playlist.app.utilities
import kotlin.NumberFormatException

fun getId() : Long? {
    return try{
        readLine()!!.toLong()
    }
    catch(exception: NumberFormatException){
        null
    }
}