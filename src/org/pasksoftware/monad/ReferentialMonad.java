package org.pasksoftware.monad;

import java.util.function.Function;

public final class ReferentialMonad<A> {

    private final A value;

    private ReferentialMonad(A value) {
        this.value = value;
    }

    static <A> ReferentialMonad<A> of(A value) {
        return new ReferentialMonad<>(value);
    }

    <B> ReferentialMonad<B> flatMap(Function<A, ReferentialMonad<B>> f) {
        return f.apply(value);
    }
}
