package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book

class AuthorFilter(private val author: String): Filter {
    override fun invoke(book: Book): Boolean {
        return book.author == author
    }
}