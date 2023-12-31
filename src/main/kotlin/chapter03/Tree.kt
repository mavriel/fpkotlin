package fp.kotlin.chapter03

sealed class Tree<out A>
data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()

fun <A> size(tree: Tree<A>): Int = when (tree) {
    is Leaf -> 1
    is Branch -> 1 + size(tree.left) + size(tree.right)
}

fun maximum(tree: Tree<Int>): Int {
    fun go(xs: Tree<Int>, max: Int): Int = when (xs) {
        is Leaf -> maxOf(max, xs.value)
        is Branch -> maxOf(go(xs.left, max), go(xs.right, max))
    }
    return go(tree, Int.MIN_VALUE)
}

fun maximum2(tree: Tree<Int>): Int = when (tree) {
    is Leaf -> tree.value
    is Branch -> maxOf(maximum2(tree.left), maximum2(tree.right))
}

fun <A> depth(tree: Tree<A>): Int {
    fun go(xs: Tree<A>, depth: Int): Int = when (xs) {
        is Leaf -> depth
        is Branch -> maxOf(go(xs.left, depth + 1), go(xs.right, depth + 1))
    }
    return go(tree, 0)
}

fun <A> depth2(tree: Tree<A>): Int = when (tree) {
    is Leaf -> 0
    is Branch -> 1 + maxOf(depth2(tree.left), depth2(tree.right))
}

fun <A, B> map(tree: Tree<A>, f: (A) -> B): Tree<B> = when (tree) {
    is Leaf -> Leaf(f(tree.value))
    is Branch -> Branch(map(tree.left, f), map(tree.right, f))
}

fun <A, B> fold(ta: Tree<A>, l: (A) -> B, b: (B, B) -> B): B = when (ta) {
    is Leaf -> l(ta.value)
    is Branch -> b(fold(ta.left, l, b), fold(ta.right, l, b))
}

fun <A> sizeF(ta: Tree<A>) = fold(ta, { 1 }, { a, b -> 1 + a + b })
fun maximumF(ta: Tree<Int>) = fold(ta, { it }, { a, b -> maxOf(a, b) })
fun <A> depthF(ta: Tree<A>) = fold(ta, { 0 }, { a, b -> maxOf(a + 1, b + 1) })
fun <A> depthF2(ta: Tree<A>) = fold(ta, { 0 }, { a, b -> 1 + maxOf(a, b) })

fun <A, B> mapF(tree: Tree<A>, f: (A) -> B): Tree<B> =
    fold(tree, { Leaf(f(it)) }, { a: Tree<B>, b: Tree<B> -> Branch(a, b) })