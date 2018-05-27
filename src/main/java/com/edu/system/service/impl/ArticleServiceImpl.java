package com.edu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.ArticleRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.CategoryService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Article;
import com.edu.system.vo.Category;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Article> getArticlesByCategory(Long categoryId) throws ServiceException {
        Category category = categoryService.getCategoryById(categoryId);
        return articleRepository.findByCategory(category);
    }

    @Override
    public void create(String name, String body, Long categoryId) throws ServiceException {
        Article article = new Article();
        article.setName(name);
        article.setBody(body);
        article.setCategory(categoryService.getCategoryById(categoryId));
        articleRepository.save(article);
    }

    @Override
    public Article get(Long id) throws ServiceException {
        return articleRepository.findById(id).orElseThrow(() -> new ServiceException("Article not found: " + id));
    }

    @Override
    public Article update(Article article) {
        return articleRepository.save(article);
    }
}
