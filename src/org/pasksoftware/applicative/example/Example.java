package org.pasksoftware.applicative.example;

import java.util.Optional;
import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
        int x = 2;
        Function<Integer, String> f = Object::toString;

        // Task: applying wrapped function to wrapped value

        // Non-applicative
        Optional<Function<Integer, String>> optionalFunction = Optional.of(f);
        Optional<Integer> optional = Optional.of(x);
        // One-liner
        // Optional.of(x).flatMap(v -> Optional.of(f).map(of -> of.apply(v)));
        Optional<String> result = optional.flatMap(v -> optionalFunction.map(of -> of.apply(v)));

        // Applicative
        OptionalApplicative<Integer> applicative = OptionalApplicative.pure(x);
        OptionalApplicative<Function<Integer, String>> pure = OptionalApplicative.pure(f);
        // One-liner
        // OptionalApplicative.pure(x).apply(OptionalApplicative.pure(f));
        OptionalApplicative<String> applicativeResult = applicative.apply(pure);

        assert applicativeResult.valueEquals(result);
        System.out.println("Values inside wrappers are equal");
    }
}