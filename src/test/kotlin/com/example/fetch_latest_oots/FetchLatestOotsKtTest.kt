package com.example.fetch_latest_oots

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.util.ResourceUtils.toURL
import java.io.File

class FetchLatestOotsKtTest {

    @Test
    fun `should extract URL object from text`() {
        // given
        val regexPatternToFind = Regex("http.*://.*\\.[a-z]*")
        val textContainingUrl = "bla bla http://www.seznam.cz bla"

        // when
        val extractedUrl = extractHttpsUrlFromText(textContainingUrl, regexPatternToFind)

        // then
        assertEquals(extractedUrl, toURL("https://www.seznam.cz"))
    }

    @Test
    fun `should throw IllegalArgumentException when no URL was found`() {
        // given/when
        val regexPatternToFind = Regex("http.*://.*\\.[a-z]*")
        val textNotContainingValidUrl = "bla bla htt://www.seznam.cz bla"

        // then
        assertThrows<IllegalArgumentException> {
            extractHttpsUrlFromText(textNotContainingValidUrl, regexPatternToFind)
        }
    }

    @Test
    fun `should return first URL from RSS feed`() {
        // given
        val rssFeedText = File("./src/test/resources/giantitp.com_comics_oots.rss").readText()

        // when
        val extractedUrl = extractHttpsUrlFromText(rssFeedText, ootsPageUrlPattern)

        // then
        assertEquals(extractedUrl, toURL("https://www.giantitp.com/comics/oots1286.html"))
    }

    @Test
    fun `should return comic image URL from HTML page source`() {
        // given
        val htmlFile = File("./src/test/resources/www.giantitp.com_comics_oots1286.html").readText()

        // when
        val extractedUrl = extractHttpsUrlFromText(htmlFile, ootsImageUrlPattern)

        // then
        assertEquals(extractedUrl, toURL("https://i.giantitp.com//comics/oots/oots1286_67e7331a7137d45fa9378e9275638898.png"))
    }
}