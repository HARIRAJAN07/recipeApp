package com.hari.recipeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hari.recipeApp.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r ORDER BY r.rating DESC NULLS LAST")
    List<Recipe> findByOrderByRatingDesc();
}