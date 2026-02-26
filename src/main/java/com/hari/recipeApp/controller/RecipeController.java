package com.hari.recipeApp.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hari.recipeApp.model.Recipe;
import com.hari.recipeApp.service.RecipeService;
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

   
    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) {

        if (recipe.getTitle() == null || recipe.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().body("Title is required");
        }

        if (recipe.getCuisine() == null || recipe.getCuisine().isEmpty()) {
            return ResponseEntity.badRequest().body("Cuisine is required");
        }

        if (recipe.getPrepTime() == null) {
            return ResponseEntity.badRequest().body("Prep time is required");
        }

        if (recipe.getCookTime() == null) {
            return ResponseEntity.badRequest().body("Cook time is required");
        }

        Recipe savedRecipe = recipeService.saveRecipe(recipe);

        return ResponseEntity.ok(savedRecipe);
    }

    
    @GetMapping("/top")
    public ResponseEntity<?> getTopRecipes(
            @RequestParam(defaultValue = "5") int limit) {

        if (limit <= 0) {
            return ResponseEntity.badRequest().body("Limit must be positive");
        }

        List<Recipe> recipes = recipeService.getTopRecipes(limit);

        Map<String, Object> response = new HashMap<>();
        response.put("data", recipes);

        return ResponseEntity.ok(response);
    }
}