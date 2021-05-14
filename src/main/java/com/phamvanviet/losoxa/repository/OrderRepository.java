package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o")
    List<Order> findAllAndSort(Pageable pageable);

    Order findOrderById(long id);

    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findAllAndSortByUserId(@Param(value = "userId") long userId, Pageable pageable);

    @Query("select count(o.id) from Order o where o.user.id = :userId")
    int countByUser(@Param(value = "userId") long userId);

    @Query(nativeQuery = true,value = "select count(1) from orders o where month(o.created_at) = ?1 and year(o.created_at ) = ?2")
    int countOrderByMonth(int month, int year);

    @Query(nativeQuery = true,value = "select coalesce(sum(o.total_price), 0) from orders o where month(o.created_at) = ?1 and year(o.created_at ) = ?2")
    int countRevenueByMonth(int month, int year);

    @Query("select sum(o.totalPrice) from Order o where o.status = 'Đã giao'")
    int countRevenue();


}
