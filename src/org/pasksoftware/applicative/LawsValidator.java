package org.pasksoftware.applicative;

import java.util.function.Function;
import java.util.function.Supplier;

public class LawsValidator {

    public static void main(String[] args) {

        int x = 5;
        Function<Integer, String> f = Object::toString;
        Function<String, Long> g = Long::valueOf;

        // Identity
        ReferentialApplicative<Integer> identity = ReferentialApplicative.pure(x).apply(ReferentialApplicative.pure(Function.identity()));
        assert identity.valueEquals(ReferentialApplicative.pure(x));
        System.out.println("Identity law fulfilled");

        // Homomorphism
        ReferentialApplicative<String> leftSide = ReferentialApplicative.pure(x).apply(ReferentialApplicative.pure(f));
        ReferentialApplicative<String> rightSide = ReferentialApplicative.pure(f.apply(x));
        assert leftSide.valueEquals(rightSide);
        System.out.println("Homomorphism law fulfilled");

        // Interchange
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ReferentialApplicative<String> interchangeLeftSide = ReferentialApplicative.pure(x).apply(ReferentialApplicative.pure(f));
        Supplier<Integer> supplier = () -> x;
        Function<Supplier<Integer>, String> tmp = i -> f.apply(i.get());
        ReferentialApplicative<String> interchangeRightSide = ReferentialApplicative.pure(supplier).apply(ReferentialApplicative.pure(tmp));
        assert interchangeLeftSide.valueEquals(interchangeRightSide);
        System.out.println("Interchange law fulfilled");

        // Composition
        // As far as I can tell it should be in line with what is expected from this Law
        ReferentialApplicative<Long> compositionLeftSide = ReferentialApplicative.pure(x).apply(ReferentialApplicative.pure(f)).apply(ReferentialApplicative.pure(g));
        ReferentialApplicative<Long> compositionRightSide = ReferentialApplicative.pure(x).apply(ReferentialApplicative.pure(f.andThen(g)));
        assert compositionLeftSide.valueEquals(compositionRightSide);
        System.out.println("Composition law fulfilled");
    }
}
