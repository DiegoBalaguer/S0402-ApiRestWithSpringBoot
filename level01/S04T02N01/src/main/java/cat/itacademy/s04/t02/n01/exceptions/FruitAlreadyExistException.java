package cat.itacademy.s04.t02.n01.exceptions;

public class FruitAlreadyExistException extends RuntimeException {
    public FruitAlreadyExistException(String name) {
        super("Fruit name is already exist in the system (" + name + ").");
    }
}
