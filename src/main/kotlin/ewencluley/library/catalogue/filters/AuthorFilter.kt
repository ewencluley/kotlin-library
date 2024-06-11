package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book
import java.util.*

class AuthorFilter(private val author: String): Filter {
    override fun invoke(book: Book): Boolean {
        return book.author.lowercase(Locale.ROOT) == author.lowercase(Locale.ROOT)
    }
}