package org.richard.thymeleaf.tamingthymeleaf

import kotlin.math.ceil

class PageResults<T>(
    private val data: List<T>,
    private val totalResult: Int,
    val pageable: Pageable
) : Page<T> {
    override fun getData(): List<T> {
        return data
    }

    override fun getTotalElements(): Int {
        return totalResult
    }

    override fun getTotalPages(): Int {
        return ceil(totalResult.toDouble() / pageable.getSize()).toInt()
    }

    override fun getPrevious(): Int {
        if (!isFirst()) {
            return pageable.getNumber() - 1
        }
        return 0
    }

    override fun getNext(): Int {
        if (!isLast()) {
            return pageable.getNumber() + 1
        }
        return pageable.getNumber()
    }

    override fun getNumberOfElements(): Int {
        return data.size
    }

    override fun isFirst(): Boolean {
        return pageable.getNumber() == 0
    }

    override fun isLast(): Boolean {
        return pageable.getNumber() == getTotalPages() - 1
    }

    override fun getNumber(): Int {
        return pageable.getNumber()
    }

    override fun getSize(): Int {
        return pageable.getSize()
    }
}