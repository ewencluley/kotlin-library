package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User

/**
 * A Library orchestrates interactions between relevant entities, such as Books, Users and the Catalogue
 */
class Library {
    fun borrow(book: Book, user: User) {
        if (book.borrowedBy !== null) {
            return
        }
        book.borrowedBy = user
    }
}
