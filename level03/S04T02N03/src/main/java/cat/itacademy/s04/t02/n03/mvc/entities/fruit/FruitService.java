package cat.itacademy.s04.t02.n03.mvc.entities.fruit;

import cat.itacademy.s04.t02.n03.exceptions.FruitAlreadyExistException;
import cat.itacademy.s04.t02.n03.exceptions.FruitInvalidDataException;
import cat.itacademy.s04.t02.n03.exceptions.FruitNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Fruit add(Fruit fruit) {
        if (fruit.getName() == null || fruit.getName().trim().isEmpty()){
            throw new FruitInvalidDataException(fruit);
        } else if (fruitRepository.findByName(fruit.getName()).isPresent()) {
            throw new FruitAlreadyExistException(fruit.getName());
        }
        return fruitRepository.save(fruit);
    }

    public Fruit update(Fruit fruit) {
        if (!fruitRepository.existsById(fruit.getId())) {
            throw new FruitNotFoundException("Fruit not found with ID: " + fruit.getId());
        }
        return fruitRepository.save(fruit);
    }

    public void delete(String id) {
        if (!fruitRepository.existsById(id)) {
            throw new FruitNotFoundException("Fruit not found with ID: " + id);
        }
        fruitRepository.deleteById(id);
    }

    public Fruit findById(String id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException("Fruit not found with ID: " + id));
    }

    public Iterable<Fruit> findAll() {
        return fruitRepository.findAll();
    }
}
