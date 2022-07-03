package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/users")
class UserController {
    @GetMapping
    fun index(model: Model?): String? {
        return "users/index"
    }
}