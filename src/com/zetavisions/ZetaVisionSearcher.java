package com.zetavisions;

import com.zetavisions.model.Cuisine;
import com.zetavisions.model.Restaurant;
import com.zetavisions.model.SearchTerms;
import com.zetavisions.util.Sorter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ZetaVisionSearcher. Runs a restaurant search based on provided {@link SearchTerms}.
 * Utilizes matchers to filter out the full restaurant list for matched terms, and sorters to sort the final results.
 */
public class ZetaVisionSearcher {
    private final String cuisinesSource;
    private final String restaurantsSource;

    // matchers
    BiFunction<SearchTerms, Restaurant, Boolean> matcherRestaurantName = (
            (searchTerms, restaurant) ->
                    restaurant.getName().toLowerCase()
                            .contains(searchTerms.getRestaurantName().toLowerCase()));

    BiFunction<SearchTerms, Restaurant, Boolean> matcherStarRating = (
            (searchTerms, restaurant) -> restaurant.getCustomerRating() >= searchTerms.getStarRating());

    BiFunction<SearchTerms, Restaurant, Boolean> matcherDistance = (
            (searchTerms, restaurant) -> restaurant.getDistance() <= searchTerms.getDistanceMiles());

    BiFunction<SearchTerms, Restaurant, Boolean> matcherPriceMax = (
            (searchTerms, restaurant) -> restaurant.getPrice() <= searchTerms.getPriceMax());

    BiFunction<Integer, Restaurant, Boolean> matcherCuisine = (
            (cuisineId, restaurant) -> restaurant.getCuisineId() == cuisineId);

    public ZetaVisionSearcher() {
        this("data/cuisines.csv", "data/restaurants.csv");
    }

    public ZetaVisionSearcher(String cuisinesSource, String restaurantsSource) {
        this.cuisinesSource = cuisinesSource;
        this.restaurantsSource = restaurantsSource;
    }

    /**
     * Run a search with the provided search terms
     * @param searchTerms parameters for the search
     * @return a list of {@link Restaurant}s matching the search parameters, sorted by best.
     */
    public List<Restaurant> search(SearchTerms searchTerms) {
        // possible future todo: lift csv loading out of search run. this would allow caching for recent searches

        // load cuisines ...
        Map<String, Cuisine> cuisineMap = loadCuisines().stream()
                .collect(Collectors.toMap(c -> c.getName().toLowerCase(), Function.identity()));
        System.out.println("Loaded " + cuisineMap.size() + " cuisines");

        // load restaurants list ...
        List<Restaurant> restaurants = loadRestaurantsEntire();
        System.out.println("Loaded " + restaurants.size() + " restaurants");

        // run matchers
        restaurants = runMatchers(searchTerms, getCuisineIdFromSearchTerm(searchTerms, cuisineMap), restaurants);

        // run sorters
        restaurants = runSorters(restaurants);

        return restaurants;
    }

//    @VisibleForTesting
    protected int getCuisineIdFromSearchTerm(SearchTerms searchTerms, Map<String, Cuisine> cuisineMap) {
        // todo: make this better
        int searchTermCuisineId = -1;
        if (!searchTerms.getCuisine().isEmpty()) {
            Optional<String> cuisineMatch = cuisineMap.keySet().stream()
                    .filter(cuisine -> cuisine.contains(searchTerms.getCuisine().toLowerCase())).findFirst();
            if (cuisineMatch.isPresent()) {
                searchTermCuisineId = cuisineMap.get(cuisineMatch.get()).getId();
            }
        }
        return searchTermCuisineId;
    }

    //    @VisibleForTesting
    protected List<Restaurant> runMatchers(SearchTerms searchTerms, int cuisineId, List<Restaurant> restaurants) {
        System.out.println("Filtering " + restaurants.size() + " restaurants with " + searchTerms);

        // todo: error handling. matchers assume search terms are valid
        return restaurants.stream()
                .filter((restaurant) -> matcherRestaurantName.apply(searchTerms, restaurant))
                .filter((restaurant) -> matcherStarRating.apply(searchTerms, restaurant))
                .filter((restaurant) -> matcherDistance.apply(searchTerms, restaurant))
                .filter((restaurant) -> matcherPriceMax.apply(searchTerms, restaurant))
                .filter((restaurant) -> cuisineId > 0 ? matcherCuisine.apply(cuisineId, restaurant) : true)
                .collect(Collectors.toList());
    }

    //    @VisibleForTesting
    protected List<Restaurant> runSorters(List<Restaurant> restaurants) {
        System.out.println("Sorting...");
        // note: sorters must run backwards from desired sort order

        // todo: maintainability: make sorter subclasses that implement a main 'sort' method.
        // we can then keep an ordered list of sorters and loop through each subclass's 'sort'

        // 1. random
        restaurants.sort(Sorter::random);

        // 2. price
        restaurants.sort(Sorter::priceAsc);

        // 3. rating
        restaurants.sort(Sorter::ratingDesc);

        // 4. distance
        restaurants.sort(Sorter::distanceAsc);

        return restaurants;
    }

    //    @VisibleForTesting
    protected List<Cuisine> loadCuisines() {
        try {
            return Files.lines(Paths.get(cuisinesSource))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(lineParts -> new Cuisine(Integer.parseInt(lineParts[0]), lineParts[1]))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("error loading cuisines: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //    @VisibleForTesting
    protected List<Restaurant> loadRestaurantsEntire() {
        try {
            return Files.lines(Paths.get(restaurantsSource))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(lineParts -> new Restaurant(
                            lineParts[0],
                            Integer.parseInt(lineParts[1]),
                            Integer.parseInt(lineParts[2]),
                            Integer.parseInt(lineParts[3]),
                            Integer.parseInt(lineParts[4])
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("error loading restaurants: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
