package com.edu.system.service;

import com.edu.system.vo.Info;

public interface InfoService {

    void create(String name, String body, Long articleId) throws ServiceException;
    Info get(Long id) throws ServiceException;
    void delete(Long id) throws ServiceException;
    Info findFirst(Long articleId) throws ServiceException;
}
