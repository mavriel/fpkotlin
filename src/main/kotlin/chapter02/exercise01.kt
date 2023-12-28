package fp.kotlin.chapter02

fun fib(i: Int): Int {
    tailrec fun go(n: Int, prev: Int, acc: Int): Int {
        if (n > i) {
            return acc
        }
        if (n == 0 || n == 1) {
            return go(n + 1, 0, n)
        }
        return go(n + 1, acc, prev + acc)
    }

    return go(0, 0, 0)
}

fun fib2(i: Int): Int {
    tailrec fun go(n: Int, acc: Int, next: Int): Int {
        if (n == 0) {
            return acc
        }
        return go(n - 1, next, next + acc)
    }
    return go(i, 0, 1)
}

fun main() {
    println((0..10).map(::fib))
    println((0..10).map(::fib2))
}