package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book

class BorrowedFilter : Filter {
    override fun invoke(book: Book): Boolean {
        return book.borrowedBy !== null
    }
}