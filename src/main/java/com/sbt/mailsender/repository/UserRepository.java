package com.sbt.mailsender.repository;

import com.sbt.mailsender.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    List<User> findAllByName(String name);

    List<User> findAllBybirthdateAfter(LocalDate date);

    @Query(value = "SELECT * FROM users" +
            " WHERE  email is not null" +
            " AND  EXTRACT(MONTH  from birthdate) = :m" +
            " AND EXTRACT(DAY from birthdate) = :d", nativeQuery = true)
    List<User> findByMatchMonthAndMatchDay(@Param("m") int month, @Param("d") int day);
}
