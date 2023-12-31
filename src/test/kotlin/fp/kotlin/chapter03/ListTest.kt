package fp.kotlin.chapter03

import kotlin.test.Test
import kotlin.test.assertEquals

class ListTest {
    private val list = List.of(1, 20, 300, 4000)

    @Test
    fun of() {
        assertEquals(1, list.get(0))
        assertEquals(20, list.get(1))
        assertEquals(300, list.get(2))
        assertEquals(4000, list.get(3))
    }

    @Test
    fun tail() {
        assertEquals(20, tail(list).get(0))
        assertEquals(300, tail(tail(list)).get(0))
        assertEquals(4000, tail(tail(tail(list))).get(0))
    }

    @Test
    fun setHead() {
        assertEquals(100, setHead(list, 100).get(0))
    }

    @Test
    fun drop() {
        assertEquals(20, drop(list, 1).get(0))
        assertEquals(300, drop(list, 2).get(0))
        assertEquals(4000, drop(list, 3).get(0))
    }

    @Test
    fun dropWhile() {
        assertEquals(4000, dropWhile(list) { it < 4000 }.get(0))
    }

    @Test
    fun init() {
        assertEquals(null, init(list).get(3))
        assertEquals(300, init(list).get(2))
        assertEquals(20, init(list).get(1))
        assertEquals(1, init(list).get(0))
        assertEquals(4000, list.get(3))
    }

    @Test
    fun length() {
        assertEquals(4, length(list))
    }

    @Test
    fun sum2_product2() {
        assertEquals(4321, sum2(list))
        assertEquals(24000000, product2(list))
    }

    @Test
    fun revert() {
        val l = revert(list)
        assertEquals(1, l.get(3))
        assertEquals(20, l.get(2))
        assertEquals(300, l.get(1))
        assertEquals(4000, l.get(0))
    }

    @Test
    fun append2() {
        val newList = append2(list, Cons(5000, Cons(6000, Nil)))
        assertEquals(5000, newList.get(4))
        assertEquals(6000, newList.get(5))
    }

    @Test
    fun append3() {
        val newList = append3(list, Cons(5000, Cons(6000, Nil)))
        assertEquals(5000, newList.get(4))
        assertEquals(6000, newList.get(5))
    }

    @Test
    fun flatten() {
        val l = flatten(Cons(List.of(9, 8, 7), Cons(empty<Int>(), Cons(List.of(6, 5, 4), Nil))))

        assertEquals(9, l.get(0))
        assertEquals(8, l.get(1))
        assertEquals(7, l.get(2))
        assertEquals(6, l.get(3))
        assertEquals(5, l.get(4))
        assertEquals(4, l.get(5))
    }

    @Test
    fun add1() {
        val l = add1(List.of(1, 2, 3, 4))
        assertEquals(2, l.get(0))
        assertEquals(3, l.get(1))
        assertEquals(4, l.get(2))
        assertEquals(5, l.get(3))
    }

    @Test
    fun mapToString() {
        val l = mapToString(List.of(1.0, 2.0, 3.0, 4.0))
        assertEquals("1.0", l.get(0))
        assertEquals("2.0", l.get(1))
        assertEquals("3.0", l.get(2))
        assertEquals("4.0", l.get(3))
    }

    @Test
    fun map() {
        val l = map(List.of(1.0, 2.0, 3.0, 4.0)) { it.toString() }
        assertEquals("1.0", l.get(0))
        assertEquals("2.0", l.get(1))
        assertEquals("3.0", l.get(2))
        assertEquals("4.0", l.get(3))
    }

    @Test
    fun filter() {
        val l = filter(List.of(1, 2, 3, 4, 5)) { it % 2 == 0 }
        assertEquals(2, l.get(0))
        assertEquals(4, l.get(1))
    }

    @Test
    fun flatMap() {
        val l = flatMap(List.of(1, 2, 3)) { List.of(it, it) }
        assertEquals(1, l.get(0))
        assertEquals(1, l.get(1))
        assertEquals(2, l.get(2))
        assertEquals(2, l.get(3))
        assertEquals(3, l.get(4))
        assertEquals(3, l.get(5))
    }

    @Test
    fun filterByFlatMap() {
        val l = filterByFlatMap(List.of(1, 2, 3, 4, 5)) { it % 2 == 0 }
        assertEquals(2, l.get(0))
        assertEquals(4, l.get(1))
    }

    @Test
    fun zipAdd() {
        val l = zipAdd(List.of(1, 2, 3), List.of(4, 5, 6))
        assertEquals(5, l.get(0))
        assertEquals(7, l.get(1))
        assertEquals(9, l.get(2))
    }

    @Test
    fun zipWith() {
        val l = zipWith(List.of(1, 2, 3), List.of(4, 5, 6)) { a, b -> a + b }
        assertEquals(5, l.get(0))
        assertEquals(7, l.get(1))
        assertEquals(9, l.get(2))
    }
}