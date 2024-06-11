package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.catalogue.Catalogue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ServiceTest {
    @Test
    @DisplayName("Given a book in the catalogue, a user can borrow it")
    fun givenBookCanBorrowIt() {
        val catalogue = mockk<Catalogue>()
        val library = mockk<Library>()

        val isbn = "978-3-16-148410-0"
        val book = Book(isbn)

        every { catalogue.findByIsbn(isbn) } returns listOf(book)
        every { library.borrow(book, Service.user) } returns Library.BorrowResult.Success

        val service = Service(catalogue, library)
        val result = service.borrowBook(isbn)
        assert(result is Service.Result.BorrowSuccess)
        verify { catalogue.findByIsbn(isbn) }
        verify { library.borrow(book, Service.user) }
    }

    @Test
    @DisplayName("Given a matching book in the catalogue and borrowing fails, expect failure result")
    fun givenBookAndBorrowingFails() {
        val catalogue = mockk<Catalogue>()
        val library = mockk<Library>()

        val isbn = "978-3-16-148410-0"
        val book = Book(isbn)

        every { catalogue.findByIsbn(isbn) } returns listOf(book)
        every { library.borrow(book, Service.user) } returns Library.BorrowResult.Failure("Failed to borrow book")

        val service = Service(catalogue, library)
        val result = service.borrowBook(isbn)
        assert(result is Service.Result.BorrowFailure)
        verify { catalogue.findByIsbn(isbn) }
        verify { library.borrow(book, Service.user) }
    }
}