package com.heiko.placelocator.parser

import com.heiko.placelocator.exceptions.GoogleAPILocatorException

/**
 * This is an util class
 */
class ParserBuilder {

    /**
     * Creates a {@link ResponseParser} object according to configuration parameters.
     *
     * @param config {@code ConfigObject} with configuration parameters
     * @return specified format parser
     * @throws GoogleAPILocatorException if xml format is chosen cause it isn't implemented
     */
    ResponseParser get(ConfigObject config) {
        final ResponseParser parser

        if (config.format == 'json') {
            parser = new JSONResponseParser()
        } else {
            throw new GoogleAPILocatorException("The script doesn't have a parser implementation for such a format ($config.format)")
        }

        return parser
    }
}
