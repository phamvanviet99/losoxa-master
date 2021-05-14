package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p")
    List<Product> findAllAndSort(Pageable pageable);

    @Query("select p from Product p order by p.quantitySold desc")
    List<Product> findAllPopular(Pageable pageable);

    @Query("select p from Product p where p.category.id = :id")
    List<Product> findAllByCategoryId(@Param(value = "id") Long id, Pageable pageable);

    @Query("select p from Product p where p.category.id = :id order by p.quantitySold desc")
    List<Product> findAllPopularByCategoryId(@Param(value = "id") Long id, Pageable pageable);

    @Query("select count(p) from Product p where p.category.id = :id")
    long countProductByCategory(Long id);

    @Query("select count(p) from Product p where p.unitPrice between :min and :max")
    int countProductByFilterPrice(Long min, Long max);

    @Query("select p from Product p where p.id = :id")
    Product findOneById(@Param(value = "id") Long id);

    List<Product> findByCategory_Id(Long categoryId);

    Product findProductByName(String name);

    @Query("select p from Product p where p.unitPrice between :min and :max")
    List<Product> findAllAndSortFilterPrice(Pageable pageable, @Param(value = "min") long min, @Param(value = "max") long max);

    @Query("select p from Product p where p.id <> :id and p.category.id =:categoryId")
    List<Product> findProductRelate(@Param(value = "id") Long id, @Param(value = "categoryId") Long categoryId);

    //search
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.category.name) LIKE %?1% order by p.quantitySold desc")
    List<Product> search(String keyword, Pageable pageable);

    @Query("select p from Product p order by p.quantitySold desc")
    List<Product> findAllAndSortByQuantitySold();

    @Query(nativeQuery = true,value = "select count(1) from products p where month(p.created_at) = ?1 and year(p.created_at ) = ?2")
    int countProductByMonth(int month, int year);

    @Query("select sum(p.quantitySold) from Product p")
    int countQuantitySold();

    @Query(value = "select l.product.name, l.product.unitPrice, sum (l.quantity) as countOrder, l.product.id " +
            " from LineItem l where l.createdAt between ?1 and ?2 group by l.product order by countOrder desc ")
    List<Tuple> findAllByOrderAndPredictMonth(Date starDate, Date endDate);



}
