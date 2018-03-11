package com.huneau.kakebo.category;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    public ModelMapper mapper;

    @GetMapping(value = "/categories")
    public List<CategoryDTO> getAllCategories() {
        return service.getAllCategories().stream().map(o -> mapper.map(o, CategoryDTO.class)).collect(Collectors.toList());
    }

    @PostMapping(value = "/categories")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        final Category category = service.saveCategory(mapper.map(categoryDTO, Category.class));
        return mapper.map(category, CategoryDTO.class);
    }

    @GetMapping(value = "/categories/{id}")
    public CategoryDTO get(@PathVariable Long id) {
        return mapper.map(service.findCategory(id), CategoryDTO.class);
    }

    @DeleteMapping(value = "/categories/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCategory(id);
    }

    @PutMapping(value = "/categories/{id}")
    public CategoryDTO update(@PathVariable Long id, @RequestBody CategoryDTO request) {
        Category category = mapper.map(request, Category.class);
        Category categoryUpdated = service.updateCategory(id, category);
        return mapper.map(categoryUpdated, CategoryDTO.class);
    }

    @GetMapping("/subcategories")
    public List<SubCategoryDTO> getAllSubCategories() {
        return service.getAllSubCategories().stream().map(o -> mapper.map(o, SubCategoryDTO.class)).collect(Collectors.toList());
    }

    @PostMapping(value = "/subcategories")
    public SubCategoryDTO createSubCategory(@RequestBody SubCategoryDTO subCategoryDTO) {
        final SubCategory subCategory = service.saveSubCategory(mapper.map(subCategoryDTO, SubCategory.class));
        return mapper.map(subCategory, SubCategoryDTO.class);
    }

    @GetMapping(value = "/subcategories/{id}")
    public SubCategoryDTO getSubCategory(@PathVariable Long id) {
        return mapper.map(service.findSubCategory(id), SubCategoryDTO.class);
    }

    @DeleteMapping(value = "/subcategories/{id}")
    public void deleteSubCategory(@PathVariable Long id) {
        service.deleteSubCategory(id);
    }

    @PutMapping(value = "/subcategories/{id}")
    public SubCategoryDTO updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryDTO request) {
        SubCategory subCategory = mapper.map(request, SubCategory.class);
        SubCategory subCategoryUpdated = service.updateSubCategory(id, subCategory);
        return mapper.map(subCategory, SubCategoryDTO.class);
    }
}

