package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Favourite;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.request.favourite.FavouriteRequest;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.response.FavouriteResponse;
import com.phamvanviet.losoxa.repository.FavouriteRepository;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.service.FavouriteService;
import com.phamvanviet.losoxa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private Converter converter;

    @Override
    public void update(FavouriteRequest favouriteRequest) {
        Product product = favouriteRepository.getProduct(favouriteRequest.getProductId());
        if (product != null){
            Favourite favourite = favouriteRepository.findFavouriteByProductId(favouriteRequest.getProductId());
            favouriteRepository.delete(favourite);
        }
        else {
            Favourite favourite = new Favourite();
            favourite.setUser(userRepository.findUserById(favouriteRequest.getUserId()));
            favourite.setProduct(productService.getProductById(favouriteRequest.getProductId()));
            favouriteRepository.save(favourite);
        }
    }

    @Override
    public List<FavouriteResponse> getListFavourite(Long id, int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Favourite> favourites = favouriteRepository.findAllAndSort(pageRequest, id);
        List<FavouriteResponse> favouriteResponses = new ArrayList<>();
        favourites.forEach(favourite -> favouriteResponses.add(converter.favouriteToResponse(favourite)));
        return favouriteResponses;

    }

    @Override
    public int getCount() {
        return (int) favouriteRepository.count();
    }

//    @Override
//    public void delete(Long productId) {
//        Favourite favourite = favouriteRepository.getOne(productId);
//        favouriteRepository.delete(favourite);
//    }
}
