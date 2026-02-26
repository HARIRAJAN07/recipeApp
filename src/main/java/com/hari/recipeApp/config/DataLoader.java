package com.hari.recipeApp.config;
import java.io.InputStream;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hari.recipeApp.model.Recipe;
import com.hari.recipeApp.repository.RecipeRepository;
@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(RecipeRepository recipeRepository,
                      ObjectMapper objectMapper) {
        this.recipeRepository = recipeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (recipeRepository.count() > 0)
             {
             return;
           }
        InputStream inputStream =
                new ClassPathResource("US_recipes_null.json").getInputStream();

        Map<String, Recipe> recipeMap =
                objectMapper.readValue(inputStream,
                        new TypeReference<Map<String, Recipe>>() {});

       for (Recipe recipe : recipeMap.values()) {
    if (recipe.getTitle() == null || recipe.getTitle().isBlank()) 
        {
        continue;
    }
    if (recipe.getCuisine() == null || recipe.getCuisine().isBlank()) {
        continue;
    }
    if (recipe.getPrepTime() == null) {
        recipe.setPrepTime(0);
    }
    if (recipe.getCookTime() == null) {
        recipe.setCookTime(0);
    }
    recipe.setTotalTime(
            recipe.getPrepTime() + recipe.getCookTime()
    );
    recipeRepository.save(recipe);
}
        System.out.println("JSON Data Loaded Successfully!");
    }
}