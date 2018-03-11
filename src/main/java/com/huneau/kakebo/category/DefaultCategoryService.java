package com.huneau.kakebo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        final List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public Category saveCategory(Category category) {
        final SubCategory subCategory = new SubCategory();
        subCategory.setName("Default");
        final Category categorySaved = categoryRepository.save(category);
        subCategory.setCategory_id(categorySaved.getId());
        subCategoryRepository.save(subCategory);
        return categorySaved;
    }

    @Override
    public Category findCategory(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        final Category originalCategory = categoryRepository.findOne(id);
        originalCategory.setName(category.getName());
        return categoryRepository.save(originalCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        final List<SubCategory> categories = new ArrayList<>();
        subCategoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory findSubCategory(Long id) {
        return subCategoryRepository.findOne(id);
    }

    @Override
    public void deleteSubCategory(Long id) {
        subCategoryRepository.delete(id);
    }

    @Override
    public SubCategory updateSubCategory(Long id, SubCategory subCategory) {
        final SubCategory originalSubCategory = subCategoryRepository.findOne(id);
        originalSubCategory.setName(subCategory.getName());
        return subCategoryRepository.save(originalSubCategory);
    }


}
