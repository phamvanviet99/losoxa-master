package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.LineItem;
import com.phamvanviet.losoxa.entity.Order;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.request.checkout.LineItemRequest;
import com.phamvanviet.losoxa.model.request.checkout.OrderRequest;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.response.OrderResponse;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import com.phamvanviet.losoxa.repository.OrderRepository;
import com.phamvanviet.losoxa.repository.ProductRepository;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.service.LineItemService;
import com.phamvanviet.losoxa.service.OrderService;
import com.phamvanviet.losoxa.service.ProductService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private LineItemService lineItemService;
    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Converter converter;


    @Override
    public List<OrderResponse> getOrderAndSort(int offset, int limit) {
        PageRequest pageable = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Order> orders = orderRepository.findAllAndSort(pageable);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(converter.orderToResponse(order));
        }
        return orderResponses;
    }

    @Override
    public void saveOrder(Long userId, OrderRequest orderRequest, List<LineItemRequest> lineItemRequests) {
        User user = userRepository.findUserById(userId);
        Order order = new Order();
        if (orderRequest.getPoint() != null) {
            user.setPoint(user.getPoint() - orderRequest.getPoint());
            order.setPoint(orderRequest.getPoint());
        }
        SecurityUtils.getPrinciple().setPoint(user.getPoint());
        order.setUser(user);
        BeanUtils.copyProperties(orderRequest, order);
        order.setCreatedAt(new Date());
        order.setStatus("Chờ xác nhận");
        Order orderSave = orderRepository.save(order);

        List<LineItem> lineItems = new ArrayList<>();

        for (LineItemRequest lineItemRequest : lineItemRequests) {
            LineItem lineItem = new LineItem();
            Product productSave = productService.getProductById(lineItemRequest.getProductId());
//            productSave.setQuantitySold(productSave.getQuantitySold()+lineItemRequest.getQuantity());//tang so luong da ban cua san pham do
            lineItem.setOrder(orderSave);
            lineItem.setProduct(productSave);
            lineItem.setQuantity(lineItemRequest.getQuantity());
            lineItem.setUnitPrice(lineItemRequest.getUnitPrice());
            lineItem.setCreatedAt(new Date());
            lineItems.add(lineItem);
        }
        lineItemService.saveAllLineItem(lineItems);

    }

    @Override
    public boolean updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findOrderById(id);
        order.setStatus(status);
        List<LineItem> lineItems = lineItemRepository.findLineItemByOrderId(order.getId());
        for (LineItem item : lineItems) {
            Product product = productRepository.findOneById(item.getProduct().getId());
            int quantity = product.getQuantity();
            if (status.equalsIgnoreCase("Chờ lấy hàng")) {
                if (quantity - (item.getQuantity()) < 0)
                    return false;
            }
            if (status.equalsIgnoreCase("Đang giao")) {
                if (quantity - (item.getQuantity()) >= 0)
                    product.setQuantity(quantity - (item.getQuantity()));
                else
                    return false;
            }
            if (status.equalsIgnoreCase("Đã giao")) {
                product.setQuantitySold(product.getQuantitySold() + item.getQuantity());
            }
        }

        if (status.equalsIgnoreCase("Đã giao")) {
            int rewardPoints = (int) (order.getTotalPrice() / 20000);
            int currentPoint = order.getUser().getPoint();
            if (rewardPoints > 0)
                order.getUser().setPoint(currentPoint + rewardPoints);
        }
        if (status.equalsIgnoreCase("Đã hủy")) {
            if(order.getPaymentType() == 2){
                int pointPlus = (int) (order.getTotalPrice() / 1000);
                order.getUser().setPoint(order.getUser().getPoint() + pointPlus);
                if (order.getPoint() != null){
                    order.getUser().setPoint(order.getUser().getPoint() + order.getPoint());
                }
            }
        }
        orderRepository.save(order);
        return true;
    }

    @Override
    public boolean cancelOrder(Long id) {
        Order order = orderRepository.findOrderById(id);
        if (order.getStatus().equalsIgnoreCase("Chờ xác nhận") || order.getStatus().equalsIgnoreCase("Chờ lấy hàng")) {
            order.setStatus("Chờ hủy");
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderResponse> getOrderAndSortByUserId(long userId, int offset, int limit) {
        PageRequest pageable = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Order> orders = orderRepository.findAllAndSortByUserId(userId, pageable);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(converter.orderToResponse(order));
        }
        return orderResponses;
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public int getCount() {
        return (int) orderRepository.count();
    }

    @Override
    public int getCountByUser(Long id) {
        return (int) orderRepository.countByUser(id);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return converter.orderToResponse(orderRepository.getOne(id));
    }

    @Override
    public Map<Integer, Integer> statistical() {
        Map<Integer, Integer> statistical = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        int month = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        simpleDateFormat = new SimpleDateFormat("YYYY");
        int year = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        int index = 0;
        for (int i = month - 1; i >= 0; i--) {
            int count = orderRepository.countOrderByMonth(month - i, year);
            index++;
            statistical.put(index, count);
        }
        return statistical;
    }

    @Override
    public Map<Integer, Integer> statisticalRevenue() {
        Map<Integer, Integer> statistical = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        int month = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        simpleDateFormat = new SimpleDateFormat("YYYY");
        int year = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        int index = 0;
        for (int i = month - 1; i >= 0; i--) {
            int count = orderRepository.countRevenueByMonth(month - i, year);
            index++;
            statistical.put(index, count);
        }
        return statistical;
    }

    @Override
    public int getCountRevenue() {
        return orderRepository.countRevenue();
    }
}
