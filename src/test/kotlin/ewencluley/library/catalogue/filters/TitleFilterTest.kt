package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TitleFilterTest {
    @Test
    @DisplayName("Given book with matching title, expect true ")
    fun matchingTitle() {
        val book = mockk<Book>()
        every { book.title } returns "The Lathe of Heaven"
        val filter = TitleFilter("The Lathe of Heaven")
        val result = filter.invoke(book)
        assertTrue(result)
    }

    @Test
    @DisplayName("Given book without matching title, expect false ")
    fun nonMatchingTitle() {
        val book = mockk<Book>()
        every { book.title } returns "The Lathe of Heaven"
        val filter = TitleFilter("The Word For World Is Forrest")
        val result = filter.invoke(book)
        assertFalse(result)
    }
}