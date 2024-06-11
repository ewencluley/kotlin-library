package ewencluley.library.catalogue.filters

import ewencluley.library.catalogue.Book
import ewencluley.library.users.User
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BorrowedFilterTest {
    @Test
    @DisplayName("Given book is borrowed, return true")
    fun borrowed() {
        val borrowedFilter = BorrowedFilter()
        val result = borrowedFilter.invoke(Book("112345", User()))
        assertTrue(result)
    }

    @Test
    @DisplayName("Given book is not borrowed, return false")
    fun notBorrowed() {
        val borrowedFilter = BorrowedFilter()
        val result = borrowedFilter.invoke(Book("112345"))
        assertFalse(result)
    }
}