package com.edu.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.system.repository.CategoryRepository;
import com.edu.system.service.CategoryService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) throws ServiceException {
        return categoryRepository.findById(id).orElseThrow(()-> new ServiceException("Cant find category by id: " + id));
    }

    @Override
    public List<Category> getAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
