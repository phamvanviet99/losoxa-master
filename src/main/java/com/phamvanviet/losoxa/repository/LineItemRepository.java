package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.LineItem;
import com.phamvanviet.losoxa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

    List<LineItem> findLineItemByOrderId(Long id);

    @Query("select l from LineItem l where l.product.id = ?1 and l.createdAt between ?2 and ?3")
    List<LineItem> findLineItemByProductIdAndTime(Long id, Date startDate, Date endDate);

    @Query("select l from LineItem l where l.order.user.id = ?1 and l.product.id = ?2")
    List<LineItem> findByUserIdAndProductId(long userId, long productId);
}
