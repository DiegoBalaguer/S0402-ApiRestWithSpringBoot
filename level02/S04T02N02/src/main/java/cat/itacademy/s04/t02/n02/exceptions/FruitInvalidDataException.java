package cat.itacademy.s04.t02.n02.exceptions;

import cat.itacademy.s04.t02.n02.mvc.entities.fruit.Fruit;

public class FruitInvalidDataException extends RuntimeException {
        public FruitInvalidDataException(Fruit fruit) {
            super("A name is needed to be able to validate");
        }
    }