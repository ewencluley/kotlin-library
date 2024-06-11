package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LibraryTest {
    @Test
    @DisplayName("Given a book that is not borrowed, user should be able to borrow it")
    fun unborrowedBook() {
        val book = Book("978-3-16-148410-0")
        val user = User()

        val library = Library()

        library.borrow(book, user)

        assertEquals(user, book.borrowedBy)
    }

    @Test
    @DisplayName("Given a book that is already borrowed, user should not be able to borrow it")
    fun alreadyBorrowedBook() {
        val book = Book("978-3-16-148410-0", User())
        val user = User()

        val library = Library()

        library.borrow(book, user)

        assert(user !== book.borrowedBy)
    }
}