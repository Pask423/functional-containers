package org.pasksoftware.monad.example;

import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
        int x = 2;

        // Task: performing operation, returning wrapped value, over the value inside the container object.

        // Non-Monad
        Function<Integer, Wrapper<String>> toString = i -> new Wrapper<>(i.toString());
        Function<String, Wrapper<Integer>> hashCode = str -> new Wrapper<>(str.hashCode());
        Wrapper<Integer> wrapper = new Wrapper<>(x);
        Wrapper<String> stringifyWrapper = toString.apply(wrapper.value);
        // One liner - Wrapper<Integer> hashCodedWrapper = hashCode.apply(toString.apply(x).value);
        Wrapper<Integer> hashCodedWrapper = hashCode.apply(stringifyWrapper.value);


        // Monad
        Function<Integer, WrapperMonad<String>> toStringM = i -> WrapperMonad.of(i.toString());
        Function<String, WrapperMonad<Integer>> hashCodeM = str -> WrapperMonad.of(str.hashCode());

        WrapperMonad<Integer> hashCodedWrapperMonadic = WrapperMonad.of(x)
                .flatMap(toStringM)
                .flatMap(hashCodeM);

        assert hashCodedWrapperMonadic.valueEquals(hashCodedWrapper.value);
        System.out.println("Values inside wrappers are equal");
    }
}