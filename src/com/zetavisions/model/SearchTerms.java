package com.zetavisions.model;

public class SearchTerms {
    private String restaurantName;
    private Integer starRating;
    private Integer distanceMiles;
    private Integer priceMax;
    private String cuisine;

    public SearchTerms() {
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public Integer getDistanceMiles() {
        return distanceMiles;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public String getCuisine() {
        return cuisine;
    }

    public SearchTerms withRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
        return this;
    }

    public SearchTerms withStarRating(Integer starRating) {
        this.starRating = starRating;
        return this;
    }

    public SearchTerms withDistanceMiles(Integer distanceMiles) {
        this.distanceMiles = distanceMiles;
        return this;
    }

    public SearchTerms withPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
        return this;
    }

    public SearchTerms withCuisine(String cuisine) {
        this.cuisine = cuisine;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchTerms{");
        sb.append("restaurantName='").append(restaurantName).append('\'');
        sb.append(", starRating=").append(starRating);
        sb.append(", distanceMiles=").append(distanceMiles);
        sb.append(", priceMax=").append(priceMax);
        sb.append(", cuisine='").append(cuisine).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
