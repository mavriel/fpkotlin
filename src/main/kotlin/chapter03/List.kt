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
    is Nil -> xs
    is Cons -> xs.tail
}

fun <A> setHead(xs: List<A>, x: A): List<A> = Cons(x, tail(xs))

fun <A> drop(l: List<A>, n: Int): List<A> = when (l) {
    is Nil -> l
    is Cons -> if (n == 0) l else drop(l.tail, n - 1)
}

fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> = when (l) {
    is Nil -> l
    is Cons -> if (f(l.head)) dropWhile(tail(l), f) else l
}

fun <A> append(a1: List<A>, a2: List<A>): List<A> = when (a1) {
    is Nil -> a2
    is Cons -> Cons(a1.head, append(a1.tail, a2))
}

// 앞에서부터 끝까지 모두 계산해야 되서 n만큼 시간 걸림
fun <A> init(l: List<A>): List<A> = when (l) {
    is Nil -> l
    is Cons -> when (l.tail) {
        is Nil -> Nil
        is Cons -> Cons(l.head, init(l.tail))
    }
}

fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B = when (xs) {
    is Nil -> z
    is Cons -> f(xs.head, foldRight(xs.tail, z, f))
}

tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B = when (xs) {
    is Nil -> z
    is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
}

fun sum2(xs: List<Int>) = foldLeft(xs, 0) { a, b -> a + b }

fun product2(xs: List<Int>) = foldLeft(xs, 1) { a, b -> a * b }

fun <A> length(xs: List<A>) = foldRight(xs, 0) { _, acc -> acc + 1 }

fun <A> empty(): List<A> = Nil

fun <A> revert(xs: List<A>) = foldLeft(xs, empty<A>()) { acc, a -> Cons(a, acc) }

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

// 3.13
fun <A> append2(a1: List<A>, a2: List<A>): List<A> = foldLeft(a1, a2) { acc, i -> Cons(i, acc) }
fun <A> append3(a1: List<A>, a2: List<A>): List<A> = foldRight(a1, a2) { i, acc -> Cons(i, acc) }

// 3.14
fun <A> flatten(xxs: List<List<A>>): List<A> = foldLeft(xxs, empty()) { xs1, xs2 ->
    when (xs2) {
        is Nil -> xs1
        is Cons -> append(xs1, xs2)
    }
}