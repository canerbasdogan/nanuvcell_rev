package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Store;

public record StoreDto(int id,
                       String storeName,
                       String storeAddress,
                       String storePhone) {

    public static StoreDto convert(Store from) {
        return new StoreDto(
                from.getId(),
                from.getStoreName(),
                from.getStoreAddress(),
                from.getStorePhone());
    }
}
