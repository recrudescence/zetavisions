package com.zetavisions.util;

import com.zetavisions.model.Restaurant;

//import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

//    @org.junit.jupiter.api.Test
    void distanceAsc_returnsNeg1IfR1Closer() {
        Restaurant r1 = new Restaurant("test1", -1, 100, -1, -1);
        Restaurant r2 = new Restaurant("test2", -1, 1000, -1, -1);
//        assertThat(Sorter.distanceAsc(r1, r2), equalTo(-1))
    }

//    @org.junit.jupiter.api.Test
    void distanceAsc_returns1IfR2Closer() {
        Restaurant r1 = new Restaurant("test1", -1, 1000, -1, -1);
        Restaurant r2 = new Restaurant("test2", -1, 100, -1, -1);
//        assertThat(Sorter.distanceAsc(r1, r2), equalTo(1))
    }

//    @org.junit.jupiter.api.Test
    void distanceAsc_returns0IfEqualDistance() {
        Restaurant r1 = new Restaurant("test1", -1, 100, -1, -1);
        Restaurant r2 = new Restaurant("test2", -1, 100, -1, -1);
//        assertThat(Sorter.distanceAsc(r1, r2), equalTo(0))
    }

//    @org.junit.jupiter.api.Test
    void ratingDesc() {
        // todo, like above
    }

//    @org.junit.jupiter.api.Test
    void priceAsc() {
        // todo, like above
    }
}