package org.pasksoftware.applicative.product;

import org.pasksoftware.functor.Functor;
import org.pasksoftware.util.Tuple;

import java.util.function.Function;

public final class ProductApplicative<A> implements Functor<A> {

    private final A value;

    private ProductApplicative(A value) {
        this.value = value;
    }

    static <A> ProductApplicative<A> pure(A value) {
        return new ProductApplicative<>(value);
    }

    <B> ProductApplicative<Tuple<A, B>> product(ProductApplicative<B> second) {
        Tuple<A, B> product = new Tuple<>(value, second.value);
        return pure(product);
    }

    @Override
    public <B> ProductApplicative<B> map(Function<A, B> f) {
        return pure(f.apply(value));
    }

    // For sake of asserting in LawsValidator
    public boolean valueEquals(ProductApplicative<A> compare) {
        return this.value.equals(compare.value);
    }
}
