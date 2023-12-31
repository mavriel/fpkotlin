package fp.kotlin.chapter03

import kotlin.test.Test
import kotlin.test.assertEquals

class TreeTest {
    val tree = Branch(
        Branch(
            Branch(
                Leaf("A"),
                Branch(Leaf("B"), Leaf("C"))
            ),
            Leaf("D")
        ),
        Leaf("E")
    )

    @Test
    fun size() {
        assertEquals(1, size(Leaf("A")))
        assertEquals(3, size(Branch(Leaf("A"), Leaf("B"))))
        assertEquals(9, size(tree))
        assertEquals(1, sizeF(Leaf("A")))
        assertEquals(3, sizeF(Branch(Leaf("A"), Leaf("B"))))
        assertEquals(9, sizeF(tree))
    }

    @Test
    fun maximum() {
        assertEquals(
            10, maximum(
                Branch(
                    Branch(
                        Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Leaf(10)
                    ), Leaf(0)
                )
            )
        )
        assertEquals(
            10, maximum2(
                Branch(
                    Branch(
                        Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Leaf(10)
                    ), Leaf(0)
                )
            )
        )
        assertEquals(
            10, maximumF(
                Branch(
                    Branch(
                        Branch(Leaf(1), Branch(Leaf(2), Leaf(3))), Leaf(10)
                    ), Leaf(0)
                )
            )
        )
    }

    @Test
    fun depth() {
        assertEquals(0, depth(Leaf(1)))
        assertEquals(2, depth(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))))
        assertEquals(4, depth(tree))
        assertEquals(0, depth2(Leaf(1)))
        assertEquals(2, depth2(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))))
        assertEquals(4, depth2(tree))
        assertEquals(0, depthF(Leaf(1)))
        assertEquals(2, depthF(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))))
        assertEquals(4, depthF(tree))
        assertEquals(0, depthF2(Leaf(1)))
        assertEquals(2, depthF2(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))))
        assertEquals(4, depthF2(tree))
    }
}