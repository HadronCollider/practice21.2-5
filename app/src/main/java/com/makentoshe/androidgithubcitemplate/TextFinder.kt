package com.makentoshe.androidgithubcitemplate

import org.jsoup.Jsoup

object TextFinder {
    @JvmStatic
    fun findText(data: String): String? {
        val doc = Jsoup.parse(data)
        var lyrics = doc?.body()?.getElementsByClass("lyrics")?.html()
        lyrics = lyrics?.replace("\n", "")
            ?.replace("<br>", "\n")
            ?.replace(Regex("<[^<>]*+>"), "")
            ?.replace(Regex("^\\s+"), "")
            ?.replace(Regex("\\s+$"), "")
        return lyrics
    }
}