package org.pasksoftware.functor;

import java.util.function.Function;

public final class ReferentialFunctor<A> implements Functor<A> {

    private final A value;

    public ReferentialFunctor(A value) {
        this.value = value;
    }

    @Override
    public <B> ReferentialFunctor<B> map(Function<A, B> f) {
        return new ReferentialFunctor<>(f.apply(value));
    }

    // For sake of Laws validation
    boolean valueEquals(A x) {
        return value.equals(x);
    }
}
