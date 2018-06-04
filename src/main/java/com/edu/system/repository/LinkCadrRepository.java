package com.edu.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.AbstractCadr;
import com.edu.system.vo.Article;
import com.edu.system.vo.LinkCadr;
import com.edu.system.vo.types.LinkCadrType;

public interface LinkCadrRepository extends CrudRepository<LinkCadr, Long> {
    LinkCadr findByFromCadrAndType(AbstractCadr fromCadr, LinkCadrType type);

    List<LinkCadr> findByArticle(Article article);

}