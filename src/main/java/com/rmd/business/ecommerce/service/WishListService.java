package com.rmd.business.ecommerce.service;


import com.rmd.business.ecommerce.dto.ProductDto;
import com.rmd.business.ecommerce.model.User;
import com.rmd.business.ecommerce.model.WishList;
import com.rmd.business.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService productService;

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList: wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }

    public List<Integer> getWishProductIdListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<Integer> productIds = new ArrayList<>();
        for (WishList wishList: wishLists) {
            productIds.add(productService.getProductDto(wishList.getProduct()).getProductId());
        }

        return productIds;
    }
}
