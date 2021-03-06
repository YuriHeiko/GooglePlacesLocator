package com.heiko.placelocator.initializers

/**
 * Uses a {@code ConfigSlurper} class to reads property file (Groovy script)
 * and returns a {@code ConfigObject} instance.
 */
class ConfigReader {

    /**
     * Reads properties from a specified url and fills the property map.
     * Uses {@code ConfigSlurper} class.
     *
     * @param location The original location of the Script as a URL
     * @return The ConfigObject instance
     */
    static Map read(final URL location) {
        new ConfigSlurper().parse(location)
    }

    /**
     * Reads properties from the specified groovy script file.
     *
     * @return The ConfigObject instance
     */
    static Map read(final File file) {
        read(file.toURI().toURL())
    }
}