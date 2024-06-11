package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AuthorFilterTest {
    @Test
    @DisplayName("Given book with matching author, returns true")
    fun testBookWithMatchingAuthor() {
        val book = mockk<Book>()
        every { book.author } returns "Frank Herbert"
        val filter = AuthorFilter("Frank Herbert")
        val result = filter.invoke(book)
        assertTrue(result)
    }

    @Test
    @DisplayName("Given book without matching author, returns false")
    fun testBookWithoutMatchingAuthor() {
        val book = mockk<Book>()
        every { book.author } returns "Ursula K. Le Guin"
        val filter = AuthorFilter("Frank Herbert")
        val result = filter.invoke(book)
        assertFalse(result)
    }

}