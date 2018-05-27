package com.edu.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.edu.system.vo.Article;
import com.edu.system.vo.Test;

public interface TestRepository extends CrudRepository<Test, Long> {
    List<Test> findByArticle(Article article);

    @Query("select t.nextTest from Test t where t.id = :id")
    Test findByPrevious(@Param("id") Long id);

    List<Test> findByNextTestIsNull();
}