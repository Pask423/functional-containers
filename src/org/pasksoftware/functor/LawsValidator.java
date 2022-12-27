package org.pasksoftware.functor;


import java.util.function.Function;

public class LawsValidator {

    public static void main(String[] args) {

        int x = 5;

        // Identity
        ReferentialFunctor<Integer> identity = new ReferentialFunctor<>(x).map(Function.identity());
        assert identity.valueEquals(x);
        System.out.println("Identity law fulfilled");

        // Associativity
        Function<Integer, String> f = Object::toString;
        Function<String, Long> g = Long::valueOf;
        ReferentialFunctor<Long> leftSide = new ReferentialFunctor<>(x).map(f).map(g);
        ReferentialFunctor<Long> rightSide = new ReferentialFunctor<>(x).map(f.andThen(g));
        assert leftSide.equals(rightSide);
        System.out.println("Associativity law fulfilled");
    }
}