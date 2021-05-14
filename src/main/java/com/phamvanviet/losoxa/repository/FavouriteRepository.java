package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Favourite;
import com.phamvanviet.losoxa.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query("select f.product from Favourite f where f.user.id =:userId")
    List<Product> getProductFavourite(@Param(value = "userId") Long userId);

    @Query("select f.product from Favourite f where f.product.id =:productId")
    Product getProduct(@Param(value = "productId") Long productId);

    Favourite findFavouriteByProductId(Long productId);

    @Query("select f from Favourite f where f.user.id =:userId")
    List<Favourite> findAllAndSort(Pageable pageable, @Param(value = "userId") Long userId);

}
