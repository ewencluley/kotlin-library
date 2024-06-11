package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book

class TitleFilter(private val title: String) : Filter {
    override fun invoke(book: Book): Boolean {
        return book.title == title
    }
}