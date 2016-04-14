package com.android.cmpe220.farm2home.util;

import com.android.cmpe220.farm2home.model.Cart;

/**
 * A helper class to retrieve the static shopping cart. Call {@code getCart()} to retrieve the shopping cart before you perform any operation on the shopping cart.
 *
 * @author Tony
 */
public class CartHelper {
    private static Cart cart = new Cart();

    /**
     * Retrieve the shopping cart. Call this before perform any manipulation on the shopping cart.
     *
     * @return the shopping cart
     */
    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }

        return cart;
    }
}
