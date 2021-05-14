package com.phamvanviet.losoxa.api.web;

import com.phamvanviet.losoxa.model.request.favourite.FavouriteRequest;
import com.phamvanviet.losoxa.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favourite")
public class FavouriteAPI {
    @Autowired
    private FavouriteService favouriteService;

    @PutMapping
    public void update(@RequestBody FavouriteRequest favouriteRequest){
        favouriteService.update(favouriteRequest);
    }

}
