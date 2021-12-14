package guru.springframework.recipeapp.bootstrap;

import guru.springframework.recipeapp.domain.*;
import guru.springframework.recipeapp.repositories.CategoryRepository;
import guru.springframework.recipeapp.repositories.RecipeRepository;
import guru.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository,
                           RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipe());
    }

    private List<Recipe> getRecipe() {
        List<Recipe> recipes = new ArrayList<>( 2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cups");

        if(!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                "\nLorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown" +
                "\nprinter took a galley of type and scrambled it to make a type specimen book. It has survived" +
                "\nnot only five centuries, but also the leap into electronic typesetting, remaining essentially" +
                "\nunchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem" +
                "\nIpsum passages, and more recently with desktop publishing software like Aldus PageMaker including" +
                "\nversions of Lorem Ipsum");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                "\nLorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown" +
                "\nprinter took a galley of type and scrambled it to make a type specimen book. It has survived");

        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(.5), teapoonUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, miced", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("freshle grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, choopped", new BigDecimal(.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Yumi Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                "\nLorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown" +
                "\nprinter took a galley of type and scrambled it to make a type specimen book. It has survived" +
                "\nnot only five centuries, but also the leap into electronic typesetting, remaining essentially" +
                "\nunchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem" +
                "\nIpsum passages, and more recently with desktop publishing software like Aldus PageMaker including" +
                "\nversions of Lorem Ipsum");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                "\nLorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown" +
                "\nprinter took a galley of type and scrambled it to make a type specimen book. It has survived");

        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Power", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(.5), teapoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Clove of Garic, choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("fnely grated orange zestr", new BigDecimal(3), tableSpoonUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }
}