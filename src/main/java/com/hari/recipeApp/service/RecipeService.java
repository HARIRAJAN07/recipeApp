package com.hari.recipeApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.recipeApp.model.Recipe;
import com.hari.recipeApp.repository.RecipeRepository;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Recipe saveRecipe(Recipe recipe) {
        recipe.setTotalTime(recipe.getPrepTime() + recipe.getCookTime());
        return recipeRepository.save(recipe);
    }
    public List<Recipe> getTopRecipes(int limit) {
    List<Recipe> allRecipes = recipeRepository.findByOrderByRatingDesc();
    List<Recipe> topRecipes = new ArrayList<>();
    for (int i = 0; i < allRecipes.size() && i < limit; i++) {
        topRecipes.add(allRecipes.get(i));
    }
    return topRecipes;
     }
}