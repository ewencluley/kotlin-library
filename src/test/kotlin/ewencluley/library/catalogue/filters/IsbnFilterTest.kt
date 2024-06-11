package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class IsbnFilterTest {
    @Test
    @DisplayName("Given book with matching ISBN, expect true")
    fun matching() {
        val filter = IsbnFilter("123-456-7890")
        val book = mockk<Book>()
        every { book.isbn } returns "123-456-7890"
        val result = filter.invoke(book)
        assertTrue(result);
    }

    @Test
    @DisplayName("Given book without matching ISBN, expect false")
    fun notMatching() {
        val filter = IsbnFilter("9")
        val book = mockk<Book>()
        every { book.isbn } returns "123-456-7890"
        val result = filter.invoke(book)
        assertFalse(result);
    }
}