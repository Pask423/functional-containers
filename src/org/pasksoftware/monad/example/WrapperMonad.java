package org.pasksoftware.monad.example;

import java.util.function.Function;

public final class WrapperMonad<A> {

    private final A value;

    private WrapperMonad(A value) {
        this.value = value;
    }

    static <A> WrapperMonad<A> of(A value) {
        return new WrapperMonad<>(value);
    }

    <B> WrapperMonad<B> flatMap(Function<A, WrapperMonad<B>> f) {
        return f.apply(value);
    }

    // For sake of asserting in Example
    boolean valueEquals(A x) {
        return value.equals(x);
    }
}