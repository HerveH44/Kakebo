package com.huneau.kakebo.category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category saveCategory(Category category);

    Category findCategory(Long id);

    void deleteCategory(Long id);

    Category updateCategory(Long id, Category category);

    List<SubCategory> getAllSubCategories();

    SubCategory saveSubCategory(SubCategory subCategory);

    SubCategory findSubCategory(Long id);

    void deleteSubCategory(Long id);

    SubCategory updateSubCategory(Long id, SubCategory subCategory);

}
