package cat.itacademy.s04.t02.n03.mvc.entities.fruit;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FruitRepository extends MongoRepository<Fruit, String> {

    Optional<Fruit> findByName(String name);
}