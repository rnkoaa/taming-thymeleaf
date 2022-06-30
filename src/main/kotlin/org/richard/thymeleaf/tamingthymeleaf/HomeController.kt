package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("pageTitle", "Taming Thymeleaf")
        model.addAttribute(
            "scientists", listOf(
                "Albert Einstein", "Neils Bohr", "James Clerk Maxwell"
            )
        )
        return "home"
    }
}