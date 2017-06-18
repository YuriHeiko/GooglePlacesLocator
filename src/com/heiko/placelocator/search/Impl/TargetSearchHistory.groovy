package com.heiko.placelocator.search.Impl

import com.heiko.placelocator.location.Places
import com.heiko.placelocator.search.SearchHistory

class TargetSearchHistory implements SearchHistory<Integer, Places> {
    private final Map<Integer, Places> map = new LinkedHashMap<>()

    @Override
    void put(Integer radius, Places places) {
        map.put radius, places
    }

    @Override
    Integer getKey(int index) {
        map.keySet()[-(++index)]
    }

    @Override
    Places getValue(int index) {
        map.values()[-(++index)]
    }

    @Override
    int getSize() {
        return map.size()
    }
}