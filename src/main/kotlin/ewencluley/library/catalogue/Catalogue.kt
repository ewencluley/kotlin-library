package ewencluley.library.catalogue

import ewencluley.library.catalogue.filters.Filter

/**
 * A catalogue of books within a library.
 *
 * This class provides functionality for storing and finding books
 */

class Catalogue(private val books: List<Book>) {
    fun find(filter: Filter): List<Book> {
        return books.filter(filter)
    }
}
