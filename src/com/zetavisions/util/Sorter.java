package com.zetavisions.util;

import com.zetavisions.model.Restaurant;

import java.util.concurrent.ThreadLocalRandom;

public class Sorter {

    /**
     * Compare two {@link Restaurant}s
     * @param o1 first restaurant
     * @param o2 second restaurant
     * @return -1, 0, 1 if first restaurant is closer, the same, or further than the second.
     */
    public static int distanceAsc(Restaurant o1, Restaurant o2) {
        return o1.getDistance() == o2.getDistance()
                ? 0
                : (o1.getDistance() < o2.getDistance() ? -1 : 1);
    }

    /**
     * Compare two {@link Restaurant}s
     * @param o1 first restaurant
     * @param o2 second restaurant
     * @return -1, 0, 1 if first restaurant is closer, the same, or further than the second.
     */
    public static int ratingDesc(Restaurant o1, Restaurant o2) {
        return o1.getCustomerRating() == o2.getCustomerRating()
                ? 0
                : (o1.getCustomerRating() > o2.getCustomerRating() ? -1 : 1);
    }

    /**
     * Compare two {@link Restaurant}s
     * @param o1 first restaurant
     * @param o2 second restaurant
     * @return -1, 0, 1 if first restaurant is closer, the same, or further than the second.
     */
    public static int priceAsc(Restaurant o1, Restaurant o2) {
        return o1.getPrice() == o2.getPrice()
                ? 0
                : (o1.getPrice() < o2.getPrice() ? -1 : 1);
    }

    /**
     * Compare two {@link Restaurant}s
     * @param o1 first restaurant
     * @param o2 second restaurant
     * @return -1, 0, 1 if first restaurant is closer, the same, or further than the second.
     */
    public static int random(Restaurant o1, Restaurant o2) {
        return ThreadLocalRandom.current().nextInt(-1, 2); // -1, 0, 1 (max is exclusive)
    }
}
