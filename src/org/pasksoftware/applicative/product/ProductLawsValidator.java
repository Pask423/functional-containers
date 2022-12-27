package org.pasksoftware.applicative.product;

import org.pasksoftware.util.Tuple;

public class ProductLawsValidator {

    public static void main(String[] args) {

        ProductApplicative<Integer> ax = ProductApplicative.pure(5);
        ProductApplicative<Integer> ay = ProductApplicative.pure(6);
        ProductApplicative<Integer> az = ProductApplicative.pure(7);

        // Left Identity
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ProductApplicative<Tuple<Integer, Integer>> leftIdentity = ax.product(ay);
        ProductApplicative<Integer> leftIdentityMapped = leftIdentity.map(Tuple::second);
        assert leftIdentityMapped.valueEquals(ay);
        System.out.println("Left Identity fulfilled");

        // Right Identity
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ProductApplicative<Tuple<Integer, Integer>> rightIdentity = ay.product(ax);
        ProductApplicative<Integer> rightIdentityMapped = rightIdentity.map(Tuple::first);
        assert rightIdentityMapped.valueEquals(ay);
        System.out.println("Right Identity fulfilled");


        // Associativity
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ProductApplicative<Tuple<Tuple<Integer, Integer>, Integer>> leftSide = ax.product(ay).product(az);
        ProductApplicative<Tuple<Integer, Tuple<Integer, Integer>>> rightSide = ax.product(ay.product(az));
        ProductApplicative<Tuple<Tuple<Integer, Integer>, Integer>> rightSideMapped = rightSide.map(f -> new Tuple<>(new Tuple<>(f.first(), f.second().first()), f.second().second()));
        assert leftSide.valueEquals(rightSideMapped);
        System.out.println("Associativity law fulfilled");
    }
}

