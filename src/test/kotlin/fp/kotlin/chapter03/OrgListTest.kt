package fp.kotlin.chapter03

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OrgListTest {

    @Test
    fun hasSubsequence() {
        val l = listOf(1, 2, 3, 4)

        assertTrue(hasSubsequence(l, listOf(1, 2)))
        assertTrue(hasSubsequence(l, listOf(2, 3)))
        assertTrue(hasSubsequence(l, listOf(4)))
        assertFalse(hasSubsequence(l, listOf(0)))
    }

}