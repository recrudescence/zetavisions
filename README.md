# ZetaVisions restaurant searcher
_a brief take home technical assessment for a certain company_


### Usage

A pre-built jar can be found in `out/artifacts/zetavisions_jar`.

```
> java -jar out/artifacts/zetavisions_jar/zetavisions.jar -help

Usage: searcher [-hV] [-c=<cuisine>] [-d=<distance>] [-n=<restaurantName>]
                [-p=<maxPrice>] [-s=<stars>]
ZetaVision restaurant searcher
  -c, --cuisine=<cuisine>   cuisine name
  -d, --distance=<distance> max distance
  -h, --help                Show this help message and exit.
  -n, --name=<restaurantName>
                            restaurant name
  -p, --maxprice=<maxPrice> max price
  -s, --stars=<stars>       minimum star rating
  -V, --version             Print version information and exit.
```

### general overview of how search works
1. cli arguments build `SearchTerms` and instantiates `ZetaVisionSearcher`
2. call to search function
   1. loads the data files in memory: cuisines, restaurants
   2. stream restaurants, apply search filters (matchers) to stream
   3. call sorters in order on filtered restaurants list

### some shortcuts taken 
- minimal error handling
  - assume proper input - no spelling correction
  - assume valid data files (`cuisine.csv`, `restaurants.csv`)
  - assume static data file location
  - assume no scaling data files (cuisine, restaurants -> more files)
- structural
  - match types need not scale (i.e. probably wont add more search criteria)
- cuisine id > 0

### possible improvements
  - more unit and integration tests
  - searcher can persist. this will allow caching. would need cli updates.
  - more improvements can be seen in TODO comments throughout code.