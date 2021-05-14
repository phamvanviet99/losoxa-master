package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUserNameAndStatus(String name, boolean status);

    int countAllByStatusTrue();

    @Query("select u from User u where u.status = true")
    List<User> findUserList(Pageable pageable);

    User findUserById(Long id);

    @Query("select u from User u where u.email = :email")
    User findUserEmail(@Param(value = "email") String email);

    @Query("select u from User u where u.userName = :username")
    User findUserUsername(@Param(value = "username") String username);

    User findByUserName(String username);

    User findUserByEmail(String email);

    @Query(nativeQuery = true,value = "select count(1) from users u where month(u.created_at) = ?1 and year(u.created_at ) = ?2")
    int countUserByMonth(int month, int year);

    @Query(value = "select u.userName, u.fullName, u.email, count (o.user), sum(o.totalPrice) as totalPrice from User u join Order o on u.id = o.user.id where o.createdAt between ?1 and ?2 and o.status = 'Đã giao' group by o.user order by totalPrice desc ")
    List<Tuple> findAllByOrderAndPredictMonth(Date starDate, Date endDate);

}
