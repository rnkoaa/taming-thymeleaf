package org.richard.thymeleaf.tamingthymeleaf.pagination

interface Pageable {
    fun getNumber(): Int

    fun getSize(): Int

}