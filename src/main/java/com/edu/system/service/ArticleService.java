package com.edu.system.service;

import java.util.List;

import com.edu.system.vo.Article;

public interface ArticleService {
    List<Article> getArticlesByCategory(Long categoryId) throws ServiceException;
    void create(String name, String body, Long categoryId) throws ServiceException;
    Article get(Long id) throws ServiceException;
    Article update(Article article);
}
