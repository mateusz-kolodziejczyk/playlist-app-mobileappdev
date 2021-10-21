package org.mk.playlist.app.utilities

import mu.KotlinLogging
import org.mk.playlist.app.models.song.Song
import org.mk.playlist.app.models.song.SongStore
private val logger = KotlinLogging.logger {}
// This function converts a list of Song IDs to a list of Songs
fun songIDsToSongs(songIDList: List<Long>, songStore: SongStore) : List<Song>{
    val songList = ArrayList<Song>()
    for(songID in songIDList){
        val foundSong = songStore.findOne(songID)
        if(foundSong != null){
            songList.add(foundSong)
        }
    }
    return songList
}
