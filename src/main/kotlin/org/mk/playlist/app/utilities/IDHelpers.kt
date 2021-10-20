package org.mk.playlist.app.utilities

import kotlin.random.Random


fun generateRandomId(): Long {
    // Use i Int max instead of long max to make it easier for the user to input, also no negative id's
    return Random.nextLong(0, Int.MAX_VALUE.toLong())
}