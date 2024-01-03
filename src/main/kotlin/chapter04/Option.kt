package fp.kotlin.chapter04

import kotlin.math.pow

sealed class Option<out A>

data class Some<out A>(val get: A) : Option<A>()

data object None : Option<Nothing>()


fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = when (this) {
    is Some -> Some(f(get))
    is None -> None
}

fun <A> Option<A>.getOrElse(default: () -> A): A = when (this) {
    is Some -> get
    is None -> default()
}

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> = this.map(f).getOrElse { None }

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> = this.map { Some(it) }.getOrElse { ob() }

fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> = this.flatMap { if (f(it)) Some(it) else None }

fun mean(xs: List<Double>): Option<Double> = if (xs.isEmpty()) None else Some(xs.sum() / xs.size)

fun variance(xs: List<Double>): Option<Double> = mean(xs).map { m -> xs.map { x -> (x - m).pow(2) }.sum() / xs.size }
fun variance2(xs: List<Double>): Option<Double> = mean(xs).flatMap { m -> mean(xs.map { x -> (x - m).pow(2) }) }