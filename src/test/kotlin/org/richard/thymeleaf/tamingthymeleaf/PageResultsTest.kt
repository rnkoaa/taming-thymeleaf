package org.richard.thymeleaf.tamingthymeleaf

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PageResultsTest(
) {

    private val userGeneratorService: UserGeneratorService = UserGeneratorService()

    @Test
    fun testFirstPage() {
        val users = userGeneratorService.generate(20)
        val pageResults = PageResults(users, 20, PageRequest.of(1, 10))

        assertThat(pageResults.isFirst()).isTrue
    }

    @Test
    fun testLastPage() {
        val users = userGeneratorService.generate(20)
        val pageResults = PageResults(users, 20, PageRequest.of(7, 3))

        assertThat(pageResults.isLast()).isTrue
    }

    @Test
    @DisplayName("test next page after last is same as Last Page")
    fun testNextPageAfterLastIsSameAsLast() {
        val users = userGeneratorService.generate(20)
        val pageResults = PageResults(users, 20, PageRequest.of(7, 3))

        assertThat(pageResults.isLast()).isTrue

        val nextPage = pageResults.getNext()
        assertThat(nextPage).isEqualTo(pageResults.getNumber())
    }

    @Test
    @DisplayName("test previous page before first is same as First Page")
    fun testPreviousPageBeforeFirstIsSameAsFirst() {
        val users = userGeneratorService.generate(20)
        val pageResults = PageResults(users, 20, PageRequest.of(1, 3))

        assertThat(pageResults.isFirst()).isTrue

        assertThat( pageResults.getPrevious()).isEqualTo(pageResults.getNumber())
    }
}