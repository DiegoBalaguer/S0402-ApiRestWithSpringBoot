package cat.itacademy.s04.t02.n03.exceptions;

import cat.itacademy.s04.t02.n03.mvc.entities.fruit.Fruit;

public class FruitInvalidDataException extends RuntimeException {
    public FruitInvalidDataException(Fruit fruit) {
        super("A name is needed to be able to validate");
    }
}