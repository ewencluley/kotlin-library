package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LibraryTest {
    @Test
    @DisplayName("Given a book that is not borrowed, user should be able to borrow it")
    fun unborrowedBook() {
        val book = mockk<Book>(relaxed = true)
        every { book.borrowedBy } returns null
        val user = User()

        val library = Library()

        library.borrow(book, user)

        verify(exactly = 1) { book.borrowedBy }
    }

    @Test
    @DisplayName("Given a book that is already borrowed, user should not be able to borrow it")
    fun alreadyBorrowedBook() {
        val book = mockk<Book>()
        every { book.borrowedBy } returns User()

        val user = User()

        val library = Library()

        library.borrow(book, user)

        assert(user !== book.borrowedBy)
    }

    @Test
    @DisplayName("Return success result when borrowing has been successful")
    fun borrowingHasBeenSuccessful() {
        val book = mockk<Book>(relaxed = true)
        every { book.borrowedBy } returns null

        val user = User()
        val library = Library()
        val result = library.borrow(book, user)
        assert(result is Library.BorrowResult.Success)
    }

    @Test
    @DisplayName("Return failure result when borrowing has not been successful")
    fun borrowingHasNotBeenSuccessful() {
        val book = mockk<Book>()
        every { book.borrowedBy } returns User()
        val user = User()
        val library = Library()
        val result = library.borrow(book, user)
        assert(result is Library.BorrowResult.Failure)
    }
}