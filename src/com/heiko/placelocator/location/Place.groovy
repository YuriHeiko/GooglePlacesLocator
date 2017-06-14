package com.heiko.placelocator.location

class Place {
    Map<String, Double> location
    String name
    String place_id
    List<String> types
    String vicinity

    Place(Map<String, Double> location, String name, String place_id, List<String> types, String vicinity) {
        this.location = location
        this.name = name
        this.place_id = place_id
        this.types = types
        this.vicinity = vicinity
    }

    @Override
    public String toString() {
        return "Place{" +
                "location=" + location +
                ", name='" + name + '\'' +
                ", place_id='" + place_id + '\'' +
                ", types=" + types +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
}