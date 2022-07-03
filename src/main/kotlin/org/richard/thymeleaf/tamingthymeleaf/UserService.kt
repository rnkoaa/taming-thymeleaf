package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Service

@Service
class UserService {
    private val users = mutableMapOf<UserId, User>()

    fun save(user: User): User {
        users[user.id] = user
        return user
    }

    fun findAll(): List<User> {
        return users.values.toList()
    }

    fun findById(userId: UserId): User? {
        return users[userId]
    }

    fun count(): Int {
        return users.size
    }

    fun clear() {
        users.clear()
    }

    fun find(page: Pageable): Page<User> {
        val pageNum = page.getNumber()
        val limit = page.getSize()
        val startIndex = pageNum * limit
        if (users.size < startIndex) {
            return PageResults(emptyList(), users.size, page)
        }

        val lastIndex = startIndex + limit

        try {
            val pagedData = users.values.toList()
                .slice(startIndex until lastIndex)
            return PageResults(pagedData, users.size, page)
        }catch (e: IndexOutOfBoundsException) {
            return PageResults(emptyList(), users.size, page)
        }

    }
}