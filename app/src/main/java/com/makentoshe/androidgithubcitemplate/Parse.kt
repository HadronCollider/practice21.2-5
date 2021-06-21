package com.makentoshe.androidgithubcitemplate

import com.makentoshe.androidgithubcitemplate.Connection.connect
import com.makentoshe.androidgithubcitemplate.Connection.streamToString
import org.json.JSONObject
import java.util.ArrayList

class Parse {
    companion object {
        fun parsingSong(args: String): Song {

            val m: MutableMap<String, String> = HashMap()
            m["Authorization"] =
                "Bearer rEUF2cIT7X1xdQ1CkDIRIAjreSMKauHI68IlzmpKuduZU5bmwXmxPdY_M2Ybdzub"

            val htpSongs = connect("https://api.genius.com/songs/" + args, "GET", null, m)
            var responseSong = JSONObject(streamToString(htpSongs.inputStream))
                .getJSONObject("response")
                .getJSONObject("song")
            var name = responseSong.getString("title")
            var id = responseSong.getLong("id")
            var album = responseSong.getJSONObject("album").getString("name")
            var artist = Artist(
                responseSong.getJSONObject("primary_artist").getString("name"),
                responseSong.getJSONObject("primary_artist").getLong("id")
            )
            //var descriptionArray=responseSong.getJSONObject("song").getJSONObject("description_annotation").getJSONObject("annotations").getJSONObject("0").getJSONObject("body").getJSONObject("dom").getJSONArray("children")
            var song = Song(name, id, artist)
            song.album = album
            return song
        }

        fun parsingArtist(args: String): Artist {

            val m: MutableMap<String, String> = HashMap()
            m["Authorization"] =
                "Bearer rEUF2cIT7X1xdQ1CkDIRIAjreSMKauHI68IlzmpKuduZU5bmwXmxPdY_M2Ybdzub"

            val htpArtist = connect("https://api.genius.com/artists/" + args, "GET", null, m)
            var responseArtist = JSONObject(streamToString(htpArtist.inputStream))
                .getJSONObject("response")
                .getJSONObject("artist")
            var name = responseArtist.getString("name")
            var id = responseArtist.getLong("id")
            var artist = Artist(name, id)
            return artist
        }

        fun parsingSearch(args: String): ArrayList<Song> {

            val m: MutableMap<String, String> = HashMap()
            m["Authorization"] =
                "Bearer rEUF2cIT7X1xdQ1CkDIRIAjreSMKauHI68IlzmpKuduZU5bmwXmxPdY_M2Ybdzub"

            val htpSearch = connect("https://api.genius.com/search?q=" + args, "GET", null, m)
            var responseSearch = JSONObject(streamToString(htpSearch.inputStream))
                .getJSONObject("response")
                .getJSONArray("hits")
            var songs: ArrayList<Song> = ArrayList()
            for (i in 0 until responseSearch.length()) {
                var id = responseSearch.getJSONObject(i).getJSONObject("result").getLong("id")
                var name =
                    responseSearch.getJSONObject(i).getJSONObject("result").getString("title")
                var artist = Artist(
                    responseSearch.getJSONObject(i).getJSONObject("result")
                        .getJSONObject("primary_artist").getString("name"),
                    responseSearch.getJSONObject(i).getJSONObject("result")
                        .getJSONObject("primary_artist").getLong("id")
                )
                songs.add(Song(name, id, artist))
            }

            return songs
        }

    }
}