package com.edu.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.edu.system.vo.Test;

public interface TestRepository extends CrudRepository<Test, Long> {
    @Query("select t.test from Test t where t.id = :testId")
    Optional<Test> findNextTest(@Param("testId") Long testId);
}