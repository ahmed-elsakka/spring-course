package sc.pizza_maker.domain;
import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        PROTEIN, VEGGIES, CHEESE
    }
}
