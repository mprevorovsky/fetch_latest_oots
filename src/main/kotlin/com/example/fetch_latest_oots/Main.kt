// The application should display the latest Order of the Stick (Oots) comic strip image in the browser.
//
// There does not seem to be any API available on the Oots server...
// Therefore, the app at /oots:
// - reads the Oots comic RSS feed and extracts the URL of the latest strip page
// - reads the page and extracts the comic image URL, which is then passed to a ThymeLeaf HTML template
// (note that hotlinking ban needs to be bypassed by using `referrerpolicy="no-referrer"`)

package com.example.fetch_latest_oots

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class FetchLatestOotsApplication

fun main(args: Array<String>) {
	runApplication<FetchLatestOotsApplication>(*args)
}


