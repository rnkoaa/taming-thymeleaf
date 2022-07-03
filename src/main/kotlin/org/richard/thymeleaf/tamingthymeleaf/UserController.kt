package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping
    fun index(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "10") limit: Int,
        model: Model): String? {
        println("Page: $page and limit: $limit")
        val pageable = PageRequest.of(page, limit)
//        val users = userService.findAll()
        model.addAttribute("userPage", userService.find(pageable))
        return "users/index"
    }
}