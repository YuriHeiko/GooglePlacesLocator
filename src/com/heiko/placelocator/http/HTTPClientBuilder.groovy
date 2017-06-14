package com.heiko.placelocator.http

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

/**
 * This is an util class
 */
class HTTPClientBuilder {

    /**
     * Creates and returns a new HTTPClient which version is
     * based on the configuration parameter - 'HTTPClient'.
     *
     * @param clientType {@code String} with a HTTP client type
     * @return a {@link HTTPClient} object
     * @throws GoogleAPILocatorException if a chosen HTTPClient isn't implemented
     */
    HTTPClient get(String clientType) {

        final HTTPClient httpClient

        if (clientType == 'simple') {
            httpClient = new SimpleHTTPClient()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have such a HTTP-client implementation ($clientType)")
        }

        return httpClient
    }
}
