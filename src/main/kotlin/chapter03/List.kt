package fp.kotlin.chapter03

sealed class List<out T> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }
    }

    abstract fun get(n: Int): T?
}

fun <A> tail(xs: List<A>): List<A> = when (xs) {
    is Nil -> Nil
    is Cons -> xs.tail
}

fun <A> setHead(xs: List<A>, x: A): List<A> = Cons(x, tail(xs))

data object Nil : List<Nothing>() {
    override fun get(n: Int): Nothing? = null
}

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>() {
    override fun get(n: Int): A? {
        if (n == 0) {
            return head
        }
        return tail.get(n - 1)
    }
}