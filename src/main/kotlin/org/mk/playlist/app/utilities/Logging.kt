package org.mk.playlist.app.utilities

import mu.KLogger
import kotlin.jvm.internal.Intrinsics

// Log all the members of a specific collection
fun <T> logAll(collection: Collection<T>, logger: KLogger){
    for (element in collection) {
        logger.info { element }
    }
}