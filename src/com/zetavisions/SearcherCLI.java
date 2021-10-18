package com.zetavisions;

import com.zetavisions.model.Cuisine;
import com.zetavisions.model.Restaurant;
import com.zetavisions.model.SearchTerms;
import picocli.CommandLine;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@CommandLine.Command(name = "searcher", mixinStandardHelpOptions = true, version = "ZetaVision 1.0",
        description = "ZetaVision restaurant searcher")
public class SearcherCLI implements Runnable {

    @CommandLine.Option(names = { "-n", "--name" }, description = "restaurant name")
    String restaurantName = "";

    @CommandLine.Option(names = { "-c", "--cuisine" }, description = "cuisine name")
    String cuisine = "";

    @CommandLine.Option(names = { "-d", "--distance" }, description = "max distance")
    Integer distance = Integer.MAX_VALUE;

    @CommandLine.Option(names = { "-s", "--stars" }, description = "minimum star rating")
    Integer stars = 0;

    @CommandLine.Option(names = { "-p", "--maxprice" }, description = "max price")
    Integer maxPrice = Integer.MAX_VALUE;

    @Override
    public void run() {
        // make the searcher
        ZetaVisionSearcher searcher = new ZetaVisionSearcher();

        System.out.println(restaurantName);

        // do the search
        SearchTerms searchTerms = new SearchTerms()
                .withRestaurantName(restaurantName)
                .withCuisine(cuisine)
                .withDistanceMiles(distance)
                .withStarRating(stars)
                .withPriceMax(maxPrice);

        List<Restaurant> results = searcher.search(searchTerms);

        // print
        System.out.printf("\nFound %d results! Here are the best 5:\n", results.size());
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            System.out.printf("\t%s\n", results.get(i));
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SearcherCLI()).execute(args);
        System.exit(exitCode);
    }

}
