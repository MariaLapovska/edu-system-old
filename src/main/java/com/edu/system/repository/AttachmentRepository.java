package com.edu.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.Article;
import com.edu.system.vo.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    List<Attachment> findByArticle(Article article);
}