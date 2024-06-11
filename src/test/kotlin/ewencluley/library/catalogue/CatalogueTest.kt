package ewencluley.library.catalogue

import ewencluley.library.catalogue.filters.Filter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CatalogueTest {
    @Test
    @DisplayName("Given an empty catalogue, find should not return any results or call the filter")
    fun givenEmptyCatalogue() {
        val filter = mockk<Filter>()
        every { filter.invoke(any()) } returns true
        val catalogue = Catalogue(emptyList())
        val result = catalogue.find(filter)
        assert(result.isEmpty())
        verify(exactly = 0) { filter.invoke(any()) }
    }

    @Test
    @DisplayName("Given a catalogue with a single book that matches the filter, find should return a single result")
    fun givenSingleMatchingBook() {
        val filter = mockk<Filter>()
        every { filter.invoke(any()) } returns true
        val book = mockk<Book>()
        val catalogue = Catalogue(listOf(book))
        val result = catalogue.find(filter)
        assertEquals(listOf(book), result)
        verify(exactly = 1) { filter.invoke(any()) }
    }

    @Test
    @DisplayName("Given a catalogue with a single book that doesn't match the filter, find should return no results")
    fun givenSingleNonMatchingBook() {
        val filter = mockk<Filter>()
        every { filter.invoke(any()) } returns false
        val book = mockk<Book>()
        val catalogue = Catalogue(listOf(book))
        val result = catalogue.find(filter)
        assert(result.isEmpty())
        verify(exactly = 1) { filter.invoke(any()) }
    }

    @Test
    @DisplayName("Given a catalogue with a multiple book that match the filter, find should return all matching books")
    fun multipleBooksThatMatchIsbn() {
        val filter = mockk<Filter>()
        every { filter.invoke(any()) } returns true andThen true andThen false
        val book1 = mockk<Book>()
        val book2 = mockk<Book>()
        val book3 = mockk<Book>()
        val catalogue = Catalogue(
            listOf(book1, book2, book3)
        )
        val result = catalogue.find(filter)
        assertEquals(
            listOf(book1, book2),
            result
        )
        verify(exactly = 3) { filter.invoke(any()) }
    }
}