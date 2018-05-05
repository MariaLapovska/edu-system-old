package com.edu.system.rest.vo;

import java.util.List;

import com.edu.system.vo.Article;
import com.edu.system.vo.Attachment;
import com.edu.system.vo.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleContent {
    private String name;
    private String body;

    private Long linkedArticleId;

    private Category category;

    private List<Attachment> attachment;
    private TestContent test;

    public static ArticleContent from(Article article){
        Article nextArticle = article.getArticle();
        ArticleContent.ArticleContentBuilder articleResponse = ArticleContent
                .builder()
                .name(article.getName())
                .body(article.getBody())
                .category(article.getCategory())
                .attachment(article.getAttachment())
                .test(TestContent.from(article.getTest()));
        if (nextArticle != null) {
            articleResponse.linkedArticleId(nextArticle.getId());
        }
        return articleResponse.build();
    }
}
