package com.zetavisions.model;

public class Restaurant {

    private String name;
    private int customerRating;
    private int distance;
    private int price;
    private int cuisineId;

    public Restaurant(String name, int customerRating, int distance, int price, int cuisineId) {
        this.name = name;
        this.customerRating = customerRating;
        this.distance = distance;
        this.price = price;
        this.cuisineId = cuisineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Restaurant{");
        sb.append("name='").append(name).append('\'');
        sb.append(", customerRating=").append(customerRating);
        sb.append(", distance=").append(distance);
        sb.append(", price=").append(price);
        sb.append(", cuisineId=").append(cuisineId);
        sb.append('}');
        return sb.toString();
    }
}
