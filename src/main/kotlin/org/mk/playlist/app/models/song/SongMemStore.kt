package org.mk.playlist.app.models.song
import mu.KotlinLogging
import org.mk.playlist.app.utilities.logAll

private val logger = KotlinLogging.logger {}
var lastId = 0L

private fun getId(): Long {
    return lastId++
}
class SongMemStore : SongStore {
    // Use a map with K,V ID, Artist to make retrieval easier.
    val songs : HashMap<Long,Song> = HashMap()

    override fun add(song: Song): Long {
        song.id = getId()
        songs[song.id] = song
        logAll(songs.values, logger)
        return 
    }
    override fun findAll() : ArrayList<Song>{
        return ArrayList(songs.values)
    }
    override fun findOne(id: Long) : Song?{
        return songs[id]
    }

    override fun deleteOne(id: Long): Boolean {
        return if(songs.remove(id) == null){
            logger.info { "Could not remove song with id [$id]" }
            false
        }
        else{
            logger.info { "Successfully removed song with id [$id]" }
            true
        }
    }

    override fun deleteSongsByArtist(id: Long) {
        for(song in songs.values){
            if(song.artistId == id){
                songs.remove(song.id)
            }
        }
    }

    override fun filter(predicate: (Song) -> Boolean): List<Song> {
        return songs.values.filter(predicate)
    }

    override fun update(song: Song){
        val foundSong = songs[song.id]
        if(foundSong != null){
            foundSong.title = song.title
            foundSong.year = song.year
            foundSong.artistId = song.artistId
        }
    }

    fun loadDummyData(){
        add(Song(title = "Call me maybe", artistId = 0L))
        add(Song(title = "Run away with me", artistId = 0L))
        add(Song(title = "Bohemian Rhapsody"))
    }
}