package com.example.fetch_latest_oots

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ImageController {
	@GetMapping("/oots")
	fun getLatestOotsImageUrl(model: Model)  {
		model.addAttribute("latestOotsUrl", fetchLatestOotsComicImage())
	}
}