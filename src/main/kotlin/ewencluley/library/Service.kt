package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.catalogue.Catalogue
import ewencluley.library.users.User

/**
 * The service orchestrates the library's functionality, managing interactions between functionality where needed and
 * exposing it to the user.
 */
class Service(private val catalogue: Catalogue, private val library: Library) {
    fun borrowBook(isbn: String): Result {
        val book = catalogue.findByIsbn(isbn)
            .first()

        when(library.borrow(book, user)) {
            is Library.BorrowResult.Success -> return Result.BorrowSuccess(book)
            is Library.BorrowResult.Failure -> TODO("Not implemented")
        }
    }

    //TODO for now we consider there to be a single user of the library, later we can implement
    // a user management system, however that seems out of scope for this exercise
    companion object {
        val user = User()
    }

    sealed class Result {
        data class BorrowSuccess(val book: Book) : Result()
    }
}