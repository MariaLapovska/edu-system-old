package com.edu.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.ArticleRepository;
import com.edu.system.repository.TestRepository;
import com.edu.system.rest.vo.ArticleContent;
import com.edu.system.service.ArticleService;
import com.edu.system.service.CategoryService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Article;
import com.edu.system.vo.Category;
import com.edu.system.vo.Test;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    private final TestRepository testRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryService categoryService, TestRepository testRepository) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
        this.testRepository = testRepository;
    }

    @Override
    public List<Article> getArticlesByCategory(Long categoryId) throws ServiceException {
        Category category = categoryService.getCategoryById(categoryId);
        return articleRepository.findByCategory(category);
    }

    @Override
    public ArticleContent create(ArticleContent content) throws ServiceException {
        if (content.getCategory() != null && content.getLinkedArticleId() != null) {
            throw new ServiceException("Linked article cant have category");
        }
        Article article = articleRepository.save(prepareArticle(content));
        return ArticleContent.from(article);
    }

    private Article prepareArticle(ArticleContent content) throws ServiceException {
        Article article = new Article();
        article.setName(content.getName());
        article.setBody(content.getBody());
        article.setCategory(content.getCategory());
        article.setAttachment(content.getAttachment());
        if (content.getLinkedArticleId() != null) {
            article.setArticle(articleRepository.findById(content.getLinkedArticleId()).orElseThrow(() -> new ServiceException("Invalid article id")));
        }
        if(content.getTest() != null) {
            Test test = new Test();
            test.setId(content.getTest().getId());
            article.setTest(test);
        }
        return article;
    }
}
