package org.mk.playlist.app.models.playlist

import org.mk.playlist.app.models.song.Song

data class Playlist (
    var id: Long = 0L,
    var name: String = "",
    var songs : HashMap<Long, Song> = HashMap(),
)

