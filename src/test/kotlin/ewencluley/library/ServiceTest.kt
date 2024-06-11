package ewencluley.library

import ewencluley.library.catalogue.Book
import ewencluley.library.catalogue.Catalogue
import ewencluley.library.users.User
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

    @Test
    @DisplayName("Given a multiple matching books the first of which is already borrowed, expect failure")
    fun givenMultipleBooksAndFirstUnavailable() {
        val catalogue = mockk<Catalogue>()
        val library = mockk<Library>()

        val isbn = "978-3-16-148410-0"
        val book1 = Book(isbn, User())
        val book2 = Book(isbn)

        every { catalogue.findByIsbn(isbn) } returns listOf(book1, book2)
        every { library.borrow(book1, Service.user) } returns Library.BorrowResult.Failure("Failed to borrow book")
        every { library.borrow(book2, Service.user) } returns Library.BorrowResult.Success

        val service = Service(catalogue, library)
        val result = service.borrowBook(isbn)
        assert(result is Service.Result.BorrowSuccess)
        verify { catalogue.findByIsbn(isbn) }
        verify(exactly = 0) { library.borrow(book1, Service.user) }
        verify { library.borrow(book2, Service.user) }
    }

    @Test
    @DisplayName("Given multiple matching books, all which are already borrowed, expect 2nd book borrowed")
    fun givenMultipleBooksAllUnavailable() {
        val catalogue = mockk<Catalogue>()
        val library = mockk<Library>()

        val isbn = "978-3-16-148410-0"
        val book1 = Book(isbn, User())
        val book2 = Book(isbn, User())

        every { catalogue.findByIsbn(isbn) } returns listOf(book1, book2)
        every { library.borrow(book1, Service.user) } returns Library.BorrowResult.Failure("Failed to borrow book")
        every { library.borrow(book2, Service.user) } returns Library.BorrowResult.Failure("Failed to borrow book")

        val service = Service(catalogue, library)
        val result = service.borrowBook(isbn)
        assert(result is Service.Result.BorrowFailure)
        verify { catalogue.findByIsbn(isbn) }
        verify(exactly = 0) { library.borrow(book1, Service.user) }
        verify(exactly = 0) { library.borrow(book2, Service.user) }
    }

    @Test
    @DisplayName("Given no matching books, expect failure")
    fun givenNoBooks() {
        val catalogue = mockk<Catalogue>()
        val library = mockk<Library>()

        val isbn = "978-3-16-148410-0"

        every { catalogue.findByIsbn(isbn) } returns emptyList()

        val service = Service(catalogue, library)
        val result = service.borrowBook(isbn)
        assert(result is Service.Result.BorrowFailure)
        verify { catalogue.findByIsbn(isbn) }
        verify(exactly = 0) { library.borrow(any(), Service.user) }
        verify(exactly = 0) { library.borrow(any(), Service.user) }
    }
}