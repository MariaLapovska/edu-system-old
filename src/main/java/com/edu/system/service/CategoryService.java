package com.edu.system.service;

import java.util.List;

import com.edu.system.vo.Category;

public interface CategoryService {
    Category getCategoryById(Long id) throws ServiceException;
    List<Category> getAll();
    Category create(String name);
}
