package com.huneau.kakebo.outcome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huneau.kakebo.category.Category;
import com.huneau.kakebo.category.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class OutcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void shouldCreateOutcome() throws Exception {

        final Category category = new Category();
        category.setName("testCategory");
        final Category categorySaved = categoryRepository.save(category);

        final OutcomeDTO dto = new OutcomeDTO();
        dto.name = "test";
        dto.notes = "test notes";
        dto.cost = 500;
        dto.category_id = categorySaved.getId();
        dto.date = new Date();

        ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(dto);

        final MockHttpServletRequestBuilder createOutcome = post("/outcomes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);


        mockMvc.perform(createOutcome)
                .andExpect(status().isOk());
    }

}