package fp.kotlin.chapter04

import kotlin.test.Test
import kotlin.test.assertEquals

class OptionTest {
    @Test
    fun map() {
        assertEquals(Some("10"), Some(10).map { it.toString() })
        assertEquals(None, None.map { it.toString() })
    }

    @Test
    fun getOrElse() {
        assertEquals(10, Some(10).getOrElse { 0 })
        assertEquals(0, None.getOrElse { 0 })
    }

    @Test
    fun flatMap() {
        assertEquals(Some("10"), Some(10).flatMap { Some(it.toString()) })
        assertEquals(None, None.flatMap { Some(it.toString()) })
    }

    @Test
    fun orElse() {
        assertEquals(Some(10), Some(10).orElse { Some(0) })
        assertEquals(Some(0), None.orElse { Some(0) })
    }

    @Test
    fun filter() {
        assertEquals(Some(10), Some(10).filter { it > 5 })
        assertEquals(None, Some(10).filter { it < 5 })
    }
}