package com.springtest.restapi.repository;

import com.springtest.restapi.domain.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PassRepository extends JpaRepository<Pass, Integer> {
    Optional<Pass> findByPassNumber(String number);

    @Modifying
    @Query("update Pass p set p.passNumber = :passNumber, p.expired = :expired where p.id =:id")
    void update(int id, String passNumber, Timestamp expired);

    @Query("SELECT p from Pass p WHERE p.expired > CURRENT_TIMESTAMP")
    List<Pass> unavailable();

    List<Pass> findByExpiredBetween(Timestamp start, Timestamp end);
}
