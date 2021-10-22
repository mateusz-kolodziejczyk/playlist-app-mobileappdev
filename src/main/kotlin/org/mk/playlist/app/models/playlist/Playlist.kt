package org.mk.playlist.app.models.playlist

import sun.java2d.pipe.SpanShapeRenderer

data class Playlist (
    var id: Long = 0L,
    var name: String = "",
    // Store IDs of all songs
    var songs : HashSet<Long> = HashSet(),
)
