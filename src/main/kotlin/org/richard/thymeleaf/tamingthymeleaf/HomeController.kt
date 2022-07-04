package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(model: Model): String {
        return "redirect:/users"
    }
}