package fp.kotlin.chapter02

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

tailrec fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    val tail = aa.tail
    if (tail.isEmpty()) {
        return true
    }
    if (!order(aa.head, tail.head)) {
        return false
    }
    return isSorted(tail, order)
}

fun <A> isSorted2(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    tailrec fun go(x: A, xs: List<A>): Boolean =
        if (xs.isEmpty()) true
        else if (!order(x, xs.head)) false
        else go(xs.head, xs.tail)

    return go(aa.head, aa.tail)
}


fun main() {
    println(isSorted((0..100).toList()) { a: Int, b: Int -> a < b })
    println(isSorted((100 downTo 0).toList()) { a: Int, b: Int -> a < b })
    println(isSorted2((0..100).toList()) { a: Int, b: Int -> a < b })
    println(isSorted2((100 downTo 0).toList()) { a: Int, b: Int -> a < b })
}