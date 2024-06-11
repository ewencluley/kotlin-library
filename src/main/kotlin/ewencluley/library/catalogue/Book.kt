package ewencluley.library.catalogue

import ewencluley.library.users.User

/**
 * A book that is stored in the library that a user can locate and borrow
 */
data class Book(val isbn: String, var borrowedBy: User?) {
    constructor(isbn: String) : this(isbn, null)
}
