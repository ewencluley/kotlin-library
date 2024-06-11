package ewencluley.library.catalogue

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CatalogueTest {
    @Test
    @DisplayName("Given an empty catalogue, findByIsbn should not return any results")
    fun givenEmptyCatalogue() {
        val catalogue = Catalogue()
        val result = catalogue.findByIsbn("978-3-16-148410-0")
        assert(result.isEmpty())
    }
}