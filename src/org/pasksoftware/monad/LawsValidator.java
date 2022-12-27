package org.pasksoftware.monad;

import java.util.function.Function;

public class LawsValidator {

    public static void main(String[] args) {

        int x = 5;
        Function<Integer, ReferentialMonad<String>> f = i -> ReferentialMonad.of(i.toString());
        Function<String, ReferentialMonad<Long>> g = s -> ReferentialMonad.of(Long.valueOf(s));

        // Left Identity
        ReferentialMonad<String> leftIdentity = ReferentialMonad.of(x).flatMap(f);
        ReferentialMonad<String> mappedX = f.apply(x);
        assert leftIdentity.equals(mappedX);
        System.out.println("Left identity law fulfilled");

        // Right Identity
        ReferentialMonad<Integer> rightIdentity = ReferentialMonad.of(x).flatMap(ReferentialMonad::of);
        ReferentialMonad<Integer> wrappedX = ReferentialMonad.of(x);
        assert rightIdentity.equals(wrappedX);
        System.out.println("Right identity law fulfilled");

        // Associativity
        ReferentialMonad<Long> leftSide = ReferentialMonad.of(x).flatMap(f).flatMap(g);
        ReferentialMonad<Long> rightSide = ReferentialMonad.of(x).flatMap(v -> f.apply(v).flatMap(g));
        assert leftSide.equals(rightSide);
        System.out.println("Associativity law fulfilled");
    }
}
