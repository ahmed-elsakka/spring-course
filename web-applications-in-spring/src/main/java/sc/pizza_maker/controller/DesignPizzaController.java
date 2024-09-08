package sc.pizza_maker.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sc.pizza_maker.domain.Ingredient;
import sc.pizza_maker.domain.Pizza;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignPizzaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("BF", "Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CN", "Chicken", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("MOZZ", "Mozzarella", Ingredient.Type.CHEESE),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();

        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "design";
    }

    @PostMapping
    public String processPizzaOrder(@Valid Pizza pizza, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
