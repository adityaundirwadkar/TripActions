package com.tripactions;

/**
 *
 */
public class ComponentProvider {

    private static TripActionsComponent sComponent;

    public static TripActionsComponent get() {
        return sComponent;
    }

    public static void set(TripActionsComponent owlComponent) {
        sComponent = owlComponent;
    }
}
