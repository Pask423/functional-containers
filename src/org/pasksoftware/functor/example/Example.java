package org.pasksoftware.functor.example;

import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
        int x = 2;
        Function<Integer, String> f = Object::toString;

        // Task: mapping over the value inside the container object.

        // Non-functor
        Wrapper<Integer> wrapper = new Wrapper<>(x);
        String mappedValue = f.apply(wrapper.value);
        // One liner - Wrapper<String> mappedWrapper = new Wrapper<>(f.apply(x));
        Wrapper<String> mappedWrapper = new Wrapper<>(mappedValue);

        // Functor
        WrapperWithFunctor<Integer> wrapperFunctor = new WrapperWithFunctor<>(x);
        WrapperWithFunctor<String> mappedWrapperFunctor = wrapperFunctor.map(f);

        assert mappedWrapperFunctor.valueEquals(mappedWrapper.value);
        System.out.println("Values inside wrappers are equal");
    }
}