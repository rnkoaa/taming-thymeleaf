package org.richard.thymeleaf.tamingthymeleaf.user

import java.util.UUID

class EditUserForm : CreateUserFormData() {
    var id: UUID? = null
    var version: Int = 0

    companion object {

        fun fromUser(user: User): EditUserForm {
            return EditUserForm().apply {
                id = user.id.value
                firstName = user.userName.firstName
                lastName = user.userName.lastName
            }
        }
    }
}