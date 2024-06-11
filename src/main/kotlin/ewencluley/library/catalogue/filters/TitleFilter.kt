package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book
import java.util.*

class TitleFilter(private val title: String) : Filter {
    override fun invoke(book: Book): Boolean {
        return book.title.lowercase(Locale.ROOT) == title.lowercase(Locale.ROOT)
    }
}