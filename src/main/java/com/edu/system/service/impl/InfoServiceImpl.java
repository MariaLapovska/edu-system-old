package com.edu.system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.AbstractCadrRepository;
import com.edu.system.repository.InfoRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.InfoService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.AbstractCadr;
import com.edu.system.vo.Info;

@Service
public class InfoServiceImpl implements InfoService {

    private final InfoRepository infoRepository;
    private final AbstractCadrRepository abstractCadrRepository;
    private final ArticleService articleService;

    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository, AbstractCadrRepository abstractCadrRepository, ArticleService articleService) {
        this.infoRepository = infoRepository;
        this.abstractCadrRepository = abstractCadrRepository;
        this.articleService = articleService;
    }

    @Override
    public void create(String name, String body, String color, Long articleId) throws ServiceException {
        Info info = new Info();
        info.setArticle(articleService.get(articleId));
        info.setName(name);
        info.setBody(body);
        info.setColor(color);
        Optional<AbstractCadr> cadr = abstractCadrRepository.findByNextTestIsNullAndNextInfoIsNull();
        if (cadr.isPresent()) {
            cadr.get().setNextInfo(infoRepository.save(info));
            abstractCadrRepository.save(cadr.get());
        } else {
            infoRepository.save(info);
        }
    }

    @Override
    public Info get(Long id) throws ServiceException {
        return infoRepository.findById(id).orElseThrow(() -> new ServiceException("asdasda"));
    }
}
