package cat.itacademy.s04.t02.n03;

import cat.itacademy.s04.t02.n03.exceptions.FruitAlreadyExistException;
import cat.itacademy.s04.t02.n03.exceptions.FruitInvalidDataException;
import cat.itacademy.s04.t02.n03.exceptions.FruitNotFoundException;
import cat.itacademy.s04.t02.n03.mvc.entities.fruit.Fruit;
import cat.itacademy.s04.t02.n03.mvc.entities.fruit.FruitRepository;
import cat.itacademy.s04.t02.n03.mvc.entities.fruit.FruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// La limpieza se realizará explícitamente con fruitRepository.deleteAll() en @BeforeEach.
public class FruitServiceIntegrationTest {

    // Inyectamos el servicio que queremos testear.
    @Autowired
    private FruitService fruitService;

    // Inyectamos el repositorio para poder limpiar la base de datos o verificar el estado directamente.
    @Autowired
    private FruitRepository fruitRepository;

    // Este método se ejecuta antes de cada test.
    // Lo usamos para limpiar la base de datos y asegurar un estado inicial conocido para cada test.
    @BeforeEach
    void setUp() {
        fruitRepository.deleteAll();
    }

    // --- Tests para el método add(Fruit fruit) ---

    @Test
    @DisplayName("Debe añadir una fruta correctamente")
    void givenNewFruit_whenAddFruit_thenFruitIsSavedAndHasId() {
        // 1. Arrange (Preparar)
        // Para MongoDB, el ID se genera automáticamente. Lo inicializamos a null.
        Fruit newFruit = new Fruit(null, "Manzana", 100);

        // 2. Act (Actuar)
        Fruit savedFruit = fruitService.add(newFruit);

        // 3. Assert (Verificar)
        assertNotNull(savedFruit); // Asegura que la fruta guardada no es nula.
        assertNotNull(savedFruit.getId()); // Asegura que se le ha asignado un ID (String).
        assertEquals("Manzana", savedFruit.getName()); // Verifica el nombre.
        assertEquals(100, savedFruit.getQuantityInKilos()); // Verifica la cantidad.

        // Verifica que la fruta realmente está en la base de datos.
        Optional<Fruit> foundInDb = fruitRepository.findById(savedFruit.getId());
        assertTrue(foundInDb.isPresent());
        assertEquals("Manzana", foundInDb.get().getName());
    }

    @Test
    @DisplayName("Debe lanzar FruitAlreadyExistException si la fruta ya existe")
    void givenExistingFruitName_whenAddFruit_thenThrowsFruitAlreadyExistException() {
        // 1. Arrange
        Fruit existingFruit = new Fruit(null, "Pera", 50);
        fruitService.add(existingFruit); // Añadimos una fruta para que ya exista.

        Fruit duplicateFruit = new Fruit(null, "Pera", 70); // Misma nombre

        // 2. Act & Assert
        // Verificamos que se lanza la excepción esperada.
        Exception exception = assertThrows(FruitAlreadyExistException.class, () -> {
            fruitService.add(duplicateFruit);
        });

        // Verificamos el mensaje de la excepción.
        String expectedMessage = "Fruit name is already exist in the system (Pera).";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Debe lanzar FruitInvalidDataException si el nombre de la fruta es nulo")
    void givenNullFruitName_whenAddFruit_thenThrowsFruitInvalidDataException() {
        // 1. Arrange
        Fruit invalidFruit = new Fruit(null, null, 30);

        // 2. Act & Assert
        Exception exception = assertThrows(FruitInvalidDataException.class, () -> {
            fruitService.add(invalidFruit);
        });

        String expectedMessage = "A name is needed to be able to validate";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Debe lanzar FruitInvalidDataException si el nombre de la fruta está vacío")
    void givenEmptyFruitName_whenAddFruit_thenThrowsFruitInvalidDataException() {
        // 1. Arrange
        Fruit invalidFruit = new Fruit(null, "", 30);

        // 2. Act & Assert
        Exception exception = assertThrows(FruitInvalidDataException.class, () -> {
            fruitService.add(invalidFruit);
        });

        String expectedMessage = "A name is needed to be able to validate";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Debe lanzar FruitInvalidDataException si el nombre de la fruta es solo espacios en blanco")
    void givenBlankFruitName_whenAddFruit_thenThrowsFruitInvalidDataException() {
        // 1. Arrange
        Fruit invalidFruit = new Fruit(null, "   ", 30);

        // 2. Act & Assert
        Exception exception = assertThrows(FruitInvalidDataException.class, () -> {
            fruitService.add(invalidFruit);
        });

        String expectedMessage = "A name is needed to be able to validate";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // --- Tests para el método update(Fruit fruit) ---

    @Test
    @DisplayName("Debe actualizar una fruta existente correctamente")
    void givenExistingFruit_whenUpdateFruit_thenFruitIsUpdated() {
        // 1. Arrange
        Fruit existingFruit = fruitService.add(new Fruit(null, "Plátano", 200)); // Añadimos una fruta
        existingFruit.setQuantityInKilos(250); // Modificamos la cantidad
        existingFruit.setName("Plátano Maduro"); // Modificamos el nombre

        // 2. Act
        Fruit updatedFruit = fruitService.update(existingFruit);

        // 3. Assert
        assertNotNull(updatedFruit);
        assertEquals(existingFruit.getId(), updatedFruit.getId()); // El ID debe ser el mismo
        assertEquals("Plátano Maduro", updatedFruit.getName());
        assertEquals(250, updatedFruit.getQuantityInKilos());

        // Verificamos que los cambios se reflejan en la base de datos
        Optional<Fruit> foundInDb = fruitRepository.findById(updatedFruit.getId());
        assertTrue(foundInDb.isPresent());
        assertEquals("Plátano Maduro", foundInDb.get().getName());
        assertEquals(250, foundInDb.get().getQuantityInKilos());
    }

    @Test
    @DisplayName("Debe lanzar FruitNotFoundException al intentar actualizar una fruta no existente")
    void givenNonExistentFruit_whenUpdateFruit_thenThrowsFruitNotFoundException() {
        // 1. Arrange
        // Para MongoDB, el ID es un String. Usamos un ID que sabemos que no existirá.
        Fruit nonExistentFruit = new Fruit("nonExistentId12345", "Mango", 50);

        // 2. Act & Assert
        Exception exception = assertThrows(FruitNotFoundException.class, () -> {
            fruitService.update(nonExistentFruit);
        });

        String expectedMessage = "Fruit not found with ID: nonExistentId12345";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // --- Tests para el método delete(String id) ---

    @Test
    @DisplayName("Debe eliminar una fruta existente correctamente")
    void givenExistingFruitId_whenDeleteFruit_thenFruitIsDeleted() {
        // 1. Arrange
        Fruit fruitToDelete = fruitService.add(new Fruit(null, "Naranja", 150)); // Añadimos una fruta

        // 2. Act
        fruitService.delete(fruitToDelete.getId());

        // 3. Assert
        // Verificamos que la fruta ya no existe en la base de datos
        assertFalse(fruitRepository.existsById(fruitToDelete.getId()));
        Optional<Fruit> foundInDb = fruitRepository.findById(fruitToDelete.getId());
        assertFalse(foundInDb.isPresent());
    }

    @Test
    @DisplayName("Debe lanzar FruitNotFoundException al intentar eliminar una fruta no existente")
    void givenNonExistentFruitId_whenDeleteFruit_thenThrowsFruitNotFoundException() {
        // 1. Arrange
        // Para MongoDB, el ID es un String.
        String nonExistentId = "nonExistentId98765";

        // 2. Act & Assert
        Exception exception = assertThrows(FruitNotFoundException.class, () -> {
            fruitService.delete(nonExistentId);
        });

        String expectedMessage = "Fruit not found with ID: nonExistentId98765";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // --- Tests para el método findById(String id) ---

    @Test
    @DisplayName("Debe encontrar una fruta existente por ID")
    void givenExistingFruitId_whenFindById_thenFruitIsFound() {
        // 1. Arrange
        Fruit fruitToAdd = fruitService.add(new Fruit(null, "Fresa", 75));

        // 2. Act
        Fruit foundFruit = fruitService.findById(fruitToAdd.getId());

        // 3. Assert
        assertNotNull(foundFruit);
        assertEquals(fruitToAdd.getId(), foundFruit.getId());
        assertEquals("Fresa", foundFruit.getName());
    }

    @Test
    @DisplayName("Debe lanzar FruitNotFoundException si la fruta no se encuentra por ID")
    void givenNonExistentFruitId_whenFindById_thenThrowsFruitNotFoundException() {
        // 1. Arrange
        // Para MongoDB, el ID es un String.
        String nonExistentId = "nonExistentId45678";

        // 2. Act & Assert
        Exception exception = assertThrows(FruitNotFoundException.class, () -> {
            fruitService.findById(nonExistentId);
        });

        String expectedMessage = "Fruit not found with ID: nonExistentId45678";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // --- Tests para el método findAll() ---

    @Test
    @DisplayName("Debe devolver una lista vacía si no hay frutas")
    void givenNoFruits_whenFindAll_thenReturnsEmptyList() {
        // 1. Arrange (Base de datos limpia por @BeforeEach)

        // 2. Act
        Iterable<Fruit> fruits = fruitService.findAll();

        // 3. Assert
        assertNotNull(fruits);
        // Convertimos Iterable a List para facilitar las aserciones
        List<Fruit> fruitList = StreamSupport.stream(fruits.spliterator(), false).toList();
        assertTrue(fruitList.isEmpty());
        assertEquals(0, fruitList.size());
    }

    @Test
    @DisplayName("Debe devolver todas las frutas existentes")
    void givenMultipleFruits_whenFindAll_thenReturnsAllFruits() {
        // 1. Arrange
        fruitService.add(new Fruit(null, "Melocotón", 120));
        fruitService.add(new Fruit(null, "Cereza", 50));
        fruitService.add(new Fruit(null, "Uva", 300));

        // 2. Act
        Iterable<Fruit> fruits = fruitService.findAll();

        // 3. Assert
        assertNotNull(fruits);
        List<Fruit> fruitList = StreamSupport.stream(fruits.spliterator(), false).toList();
        assertEquals(3, fruitList.size()); // Verificamos que hay 3 frutas.

        // Verificamos que las frutas correctas están presentes.
        assertTrue(fruitList.stream().anyMatch(f -> f.getName().equals("Melocotón")));
        assertTrue(fruitList.stream().anyMatch(f -> f.getName().equals("Cereza")));
        assertTrue(fruitList.stream().anyMatch(f -> f.getName().equals("Uva")));
    }
}
