package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Favourite;
import com.phamvanviet.losoxa.model.request.favourite.FavouriteRequest;
import com.phamvanviet.losoxa.model.response.FavouriteResponse;

import java.util.List;

public interface FavouriteService {
    void update(FavouriteRequest favouriteRequest);

    List<FavouriteResponse> getListFavourite(Long id, int offset, int limit);

    int getCount();
}
