package com.edu.system.service;

import com.edu.system.vo.Info;

public interface InfoService {

    void create(String name, String body, String color, Long articleId) throws ServiceException;
    Info get(Long id) throws ServiceException;
}
