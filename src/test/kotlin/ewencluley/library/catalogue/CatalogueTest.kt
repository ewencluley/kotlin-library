package ewencluley.library.catalogue

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CatalogueTest {
    @Test
    @DisplayName("Given an empty catalogue, findByIsbn should not return any results")
    fun givenEmptyCatalogue() {
        val catalogue = Catalogue(emptyList())
        val result = catalogue.findByIsbn("978-3-16-148410-0")
        assert(result.isEmpty())
    }

    @Test
    @DisplayName("Given a catalogue with a single book that's ISBN matches the query, findByIsbn should return a result")
    fun givenSingleMatchingBook() {
        val catalogue = Catalogue(listOf(Book("978-3-16-148410")))
        val result = catalogue.findByIsbn("978-3-16-148410")
        assertEquals(listOf(Book("978-3-16-148410")), result)
    }

    @Test
    @DisplayName("Given a catalogue with a single book that's ISBN doesn't match the query, findByIsbn should not return a result")
    fun givenSingleNonMatchingBook() {
        val catalogue = Catalogue(listOf(Book("978-3-16-148410")))
        val result = catalogue.findByIsbn("123-4-56-789012")
        assert(result.isEmpty())
    }

    @Test
    @DisplayName("Given a catalogue with a multiple book that's ISBN match the query, findByIsbn should return all matching books")
    fun multipleBooksThatMatchIsbn() {
        val catalogue = Catalogue(
            listOf(Book("978-3-16-148410-0"), Book("978-3-16-148410-0"), Book("999-9-99-999999-9"))
        )
        val result = catalogue.findByIsbn("978-3-16-148410-0")
        assertEquals(
            listOf(Book("978-3-16-148410-0"), Book("978-3-16-148410-0")),
            result
        )
    }
}