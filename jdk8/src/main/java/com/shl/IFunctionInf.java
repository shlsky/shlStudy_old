package com.shl;

import java.util.function.Supplier;

/**
 * Created by jackson on 16/11/23.
 */
public class IFunctionInf {
    private interface Defaultable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaultable {

    }

    private static class OverridableImpl implements Defaultable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaultable create( Supplier< Defaultable > supplier ) {
            return supplier.get();
        }
    }

    public static void main( String[] args ) {
        Defaultable defaulable = DefaulableFactory.create( DefaultableImpl::new);
        System.out.println( defaulable.notRequired() );

        defaulable = DefaulableFactory.create( OverridableImpl::new );
        System.out.println( defaulable.notRequired() );
    }
}
