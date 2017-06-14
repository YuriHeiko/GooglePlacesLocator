package com.heiko.placelocator.search

import com.heiko.placelocator.exceptions.GoogleAPILocatorException
import com.heiko.placelocator.http.HTTPClient
import com.heiko.placelocator.http.URLBuilder
import com.heiko.placelocator.parser.ResponseParser

/**
 * This is an util class
 */
class PlaceSearcherBuilder {

    /**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link PlaceSearcher} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     */
    Searcher get(ResponseParser responseParser, HTTPClient httpClient,
                 URLBuilder urlBuilder, ConfigObject config) {

        final Searcher searcher

        if (config.placeSearcher == 'new') {
            searcher = new PlaceSearcher(responseParser, httpClient, urlBuilder, config)
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a search algorithm" +
                    " implementation ($config.PlaceSearcher)")
        }

        return searcher
    }
}


/*
    */
/**
     * Creates the default search algorithm implementation
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return a {@link PlaceSearcher} object
     * @throws GoogleAPILocatorException if a chosen search algorithm isn't implemented
     *//*

    PlaceSearcher get(ConfigObject config) {
        final PlaceSearcher placeSearcher

        if (config.PlaceSearcher == 'simple') {
            placeSearcher = new SimplePlaceSearcher()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a search algorithm implementation ($config.PlaceSearcher)")
        }

        return placeSearcher
    }
*/