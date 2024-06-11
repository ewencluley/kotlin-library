package ewencluley.library.catalogue

/**
 * A catalogue of books within a library.
 *
 * This class provides functionality for storing and finding books
 */

class Catalogue(private val books: List<Book>) {
    fun findByIsbn(isbn: String): List<Book> {
        return books.filter { it.isbn == isbn }
    }
}
