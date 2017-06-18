package com.heiko.placelocator

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.HTTPClientFactory
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.initializers.CommandLineParser
import com.heiko.placelocator.initializers.ConfigReader
import com.heiko.placelocator.location.Places
import com.heiko.placelocator.parser.ParserFactory
import com.heiko.placelocator.parser.ResponseParser
import com.heiko.placelocator.response.Response
import com.heiko.placelocator.search.Impl.TargetSearchHistory
import com.heiko.placelocator.search.PlaceSearcherFactory
import com.heiko.placelocator.search.SearchHistory
import com.heiko.placelocator.search.SearchProcessor

/**
 * Uses Google Places Web API to find a nearest possible location
 * by GPS coordinates.
 *
 * @args command line arguments
 * @return a {@link Response} object contains nearest possible places
 * ranged from 1 to 5, if they are found. If the search results are not
 * achieved, {@code Response} object contains the error status and
 * description
 */

try {
    // Read the initial configuration from the default configuration file
    ConfigObject config = ConfigReader.read(new File('Properties.groovy'))

    // Parse command line arguments and put them into the initial configuration object
    config.merge(CommandLineParser.parse(args) as ConfigObject)

    // Get responseParser according to configuration parameters
    final ResponseParser responseParser = new ParserFactory().create(config.outputDataFormat as String)

    // Get HTTPClient
    final HTTPClient httpClient = new HTTPClientFactory().create(config.HTTPClientType as String)

    // Creates URLBuilder object and initializes it according to configuration parameters
    final URLBuilder urlBuilder = new URLBuilder(config.urlOptions as Map, config.urlPrefix as String)

    // Get SearchProcessor
    final SearchProcessor processor = new PlaceSearcherFactory().create(responseParser, httpClient, urlBuilder, config)

    // Get SearchHistory
    final SearchHistory history = new TargetSearchHistory()

    // Do search
    Places places
    while (!processor.isSearchFinished(history)) {
        int radius = processor.getNextFactor(history)
        List results = responseParser.parse(httpClient.get(urlBuilder.get(radius)) as String).results
        history.put(new Places(results, config.excludedTypes, config.initialLat, config.initialLng), radius)
    }

    new Response(history.get(Last), config.maxLocationNumber, config.gpsError)

} catch (GoogleAPILocatorException e) {
    new Response(e.errorCode, e.getMessage())
}