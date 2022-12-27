package org.pasksoftware.applicative.example;

import org.pasksoftware.functor.Functor;

import java.util.Optional;
import java.util.function.Function;

public final class OptionalApplicative<A> implements Functor<A> {

    private final Optional<A> value;

    private OptionalApplicative(A value) {
        this.value = Optional.of(value);
    }

    private OptionalApplicative() {
        this.value = Optional.empty();
    }

    static <A> OptionalApplicative<A> pure(A value) {
        return new OptionalApplicative<>(value);
    }

    <B> OptionalApplicative<B> apply(OptionalApplicative<Function<A, B>> f) {
        Optional<B> apply = f.value.flatMap(value::map);
        return apply.map(OptionalApplicative::new).orElseGet(OptionalApplicative::new);
    }

    @Override
    public <B> Functor<B> map(Function<A, B> f) {
        return apply(pure(f));
    }

    // For sake of asserting in Example
    public boolean valueEquals(Optional<A> s) {
        return value.equals(s);
    }
}
