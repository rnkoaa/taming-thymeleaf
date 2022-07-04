package org.richard.thymeleaf.tamingthymeleaf.pagination

interface Page<T> : Pageable {

    fun getData(): List<T>

    fun getTotalElements(): Int

    fun getTotalPages(): Int

    fun getPrevious(): Int

    fun getNext(): Int

    fun getNumberOfElements(): Int

    fun isFirst(): Boolean

    fun isLast(): Boolean

}