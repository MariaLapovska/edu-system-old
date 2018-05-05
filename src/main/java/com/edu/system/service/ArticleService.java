package com.edu.system.service;

import java.util.List;

import com.edu.system.rest.vo.ArticleContent;
import com.edu.system.vo.Article;

public interface ArticleService {
    List<Article> getArticlesByCategory(Long categoryId) throws ServiceException;
    ArticleContent create(ArticleContent content) throws ServiceException;
}
