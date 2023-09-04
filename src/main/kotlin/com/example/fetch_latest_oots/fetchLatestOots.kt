package com.example.fetch_latest_oots

import org.springframework.util.ResourceUtils.toURL
import java.net.URL


val ootsRssUrl = toURL("https://www.giantitp.com/comics/oots.rss")
val ootsPageUrlPattern: Regex = Regex("http://www\\.giantitp\\.com/comics/oots[0-9]*\\.html")
val ootsImageUrlPattern: Regex =
    Regex("https://i\\.giantitp\\.com//comics/oots/oots[0-9]*_[0-9;a-z]*\\.png")


    fun fetchLatestOotsComicImage(
        rssUrl: URL = ootsRssUrl,
        pageUrlPattern: Regex = ootsPageUrlPattern,
        imageUrlPattern: Regex = ootsImageUrlPattern
    ): String {
        val ootsRssFeed = rssUrl.readText()
        val ootsLatestUrl = extractHttpsUrlFromText(ootsRssFeed, pageUrlPattern)
        val ootsLatestHtml = ootsLatestUrl.readText()
        return extractHttpsUrlFromText(ootsLatestHtml, imageUrlPattern).toString()
    }


    fun extractHttpsUrlFromText(text: String, regexStringToFind: Regex): URL {
        val url = regexStringToFind.find(text)?.value
            ?.replace("http:", "https:")

        return url?.let { toURL(it) } ?: throw IllegalArgumentException("No URL found in the input text.")
    }
