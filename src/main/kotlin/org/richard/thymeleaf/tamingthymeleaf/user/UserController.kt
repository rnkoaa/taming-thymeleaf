package org.richard.thymeleaf.tamingthymeleaf.user

import org.richard.thymeleaf.tamingthymeleaf.pagination.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


@Controller
@RequestMapping("/users")
open class UserController(
    val userService: UserService,
) {

    @GetMapping
    fun index(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "10") limit: Int,
        model: Model
    ): String? {
        println("Page: $page and limit: $limit")
        val pageable = PageRequest.of(page, limit)
        model.addAttribute("userPage", userService.find(pageable))
        return "users/index"
    }

    // https://github.com/JahnelGroup/spring-boot-samples/blob/master/spring-boot-api-error/src/main/kotlin/com/example/api/errors/WebApiExceptionHandler.kt
    @PostMapping("/create")
    open fun createUserForm(
        @ModelAttribute("user") @Valid user: CreateUserFormData,
        bindingResult: BindingResult
    ): String {
        println("First Name: empty -> ${user.firstName?.isEmpty()}")
        println("Last Name: empty -> ${user.lastName?.isEmpty()}")
        if (bindingResult.hasErrors()) {
//            model.addAttribute("genders", listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER));
            return "users/edit";
        }

        println("There are no errors")
        userService.save(user.toUser());
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    fun editUser(@PathVariable("id") id: String, model: Model): String {
        val userId = UserId.of(id)
        val foundUser = userService.findById(userId) ?: throw UserNotFoundException(userId)

        val editUserForm = EditUserForm.fromUser(foundUser)
        model.addAttribute("editMode", EditMode.UPDATE)
        model.addAttribute("user", editUserForm)

        return "users/edit"
    }

    @PostMapping("/edit/{id}")
    fun doEditUser(
        @PathVariable("id") id: String,
        @Validated @ModelAttribute("user") editUserForm: EditUserForm,
        bindingResult: BindingResult,
        model: Model,
    ): String {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        val userId = UserId.of(id)
        val foundUser = userService.findById(userId) ?: throw UserNotFoundException(userId)

//        val editUserForm = EditUserForm.fromUser(foundUser)
        model.addAttribute("editMode", EditMode.UPDATE)
        model.addAttribute("user", editUserForm)

        return "users/edit"
    }

    @GetMapping("/create")
    fun createUserForm(
        model: Model,
    ): String {
        model.addAttribute("user", CreateUserFormData())
        model.addAttribute("editMode", EditMode.CREATE)
        model.addAttribute("genders", listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER))

        return "users/edit"
    }
}