package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book

class IsbnFilter(private val isbn: String): Filter {
    override fun invoke(book: Book): Boolean {
        return book.isbn == isbn
    }
}