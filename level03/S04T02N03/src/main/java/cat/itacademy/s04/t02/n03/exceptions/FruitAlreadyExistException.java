package cat.itacademy.s04.t02.n03.exceptions;

public class FruitAlreadyExistException extends RuntimeException {
    public FruitAlreadyExistException(String name) {
        super("Fruit name is already exist in the system (" + name + ").");
    }
}
