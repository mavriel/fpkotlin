package fp.kotlin.chapter03

import kotlin.test.Test
import kotlin.test.assertEquals

class ListTest {

    @Test
    fun of() {
        val list = List.of(1, 20, 300, 4000)

        assertEquals(1, list.get(0))
        assertEquals(20, list.get(1))
        assertEquals(300, list.get(2))
        assertEquals(4000, list.get(3))
    }

    @Test
    fun tail() {
        val list = List.of(1, 20, 300, 4000)

        assertEquals(20, tail(list).get(0))
        assertEquals(300, tail(tail(list)).get(0))
        assertEquals(4000, tail(tail(tail(list))).get(0))
    }

    @Test
    fun setHead() {
        val list = List.of(1, 20, 300, 4000)

        assertEquals(100, setHead(list, 100).get(0))
    }
}