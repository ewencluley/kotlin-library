package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User

/**
 * A Library orchestrates interactions between relevant entities, such as Books, Users and the Catalogue
 */
class Library {
    fun borrow(book: Book, user: User): BorrowResult {
        if (book.borrowedBy !== null) {
            return BorrowResult.Failure("Cannot borrow. This book is already on loan")
        }
        book.borrowedBy = user
        return BorrowResult.Success
    }

    sealed class BorrowResult {
        data object Success : BorrowResult()
        data class Failure(val message: String) : BorrowResult()
    }
}
