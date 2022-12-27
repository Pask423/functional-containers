package org.pasksoftware.functor.example;

import org.pasksoftware.functor.Functor;

import java.util.function.Function;

public class WrapperWithFunctor<A> implements Functor<A> {

    private final A value;

    public WrapperWithFunctor(A value) {
        this.value = value;
    }

    @Override
    public <B> WrapperWithFunctor<B> map(Function<A, B> f) {
        return new WrapperWithFunctor<>(f.apply(value));
    }

    // For sake of asserting in Example
    boolean valueEquals(A x) {
        return value.equals(x);
    }
}
