package com.hari.recipeApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrients {

    private String calories;
    private String carbohydrateContent;
    private String proteinContent;
    private String fatContent;
    
}

