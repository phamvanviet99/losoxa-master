package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Order;
import com.phamvanviet.losoxa.model.request.checkout.LineItemRequest;
import com.phamvanviet.losoxa.model.request.checkout.OrderRequest;
import com.phamvanviet.losoxa.model.response.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderResponse> getOrderAndSort(int offset, int limit);
    void saveOrder(Long userId, OrderRequest orderRequest, List<LineItemRequest> lineItemRequests);
    boolean updateOrderStatus(Long id, String status);
    boolean cancelOrder(Long id);
    List<OrderResponse> getOrderAndSortByUserId(long userId, int offset, int limit);
    Order findOrderById(Long id);
    int getCount();
    int getCountByUser(Long id);
    OrderResponse getOrderById(Long id);
    Map<Integer, Integer> statistical();
    Map<Integer, Integer> statisticalRevenue();
    int getCountRevenue();

}
