package org.richard.thymeleaf.tamingthymeleaf.pagination

class PageRequest(private val page: Int, private val limit: Int) : Pageable {

    companion object {
        fun of(page: Int, limit: Int): PageRequest {
            val correctedPage = if (page <= 0) {
                0
            } else {
                page - 1
            }
            return PageRequest(correctedPage, limit)
        }
    }

    override fun getNumber(): Int {
        return page
    }

    override fun getSize(): Int {
        return limit
    }
}