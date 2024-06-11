package ewencluley.library.catalogue

import ewencluley.library.users.User

/**
 * A book that is stored in the library that a user can locate and borrow
 */
data class Book(val isbn: String, var author:String, var title: String, var borrowedBy: User?) {
    constructor(isbn: String, author:String, title: String) : this(isbn, author, title, null)
}
