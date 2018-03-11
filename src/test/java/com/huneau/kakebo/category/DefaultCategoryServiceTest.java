package com.huneau.kakebo.category;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DefaultCategoryServiceTest {

    @Autowired
    private DefaultCategoryService categoryService;

    @Test
    public void saveCategory() {
        final Category category = new Category();
        category.setName("testCategory");

        categoryService.saveCategory(category);

        Assert.assertEquals(1, categoryService.getAllSubCategories().size());

    }
}