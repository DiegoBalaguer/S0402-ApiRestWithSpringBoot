package cat.itacademy.s04.t02.n01.mvc.entities.fruit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FruitRepository extends JpaRepository<Fruit, Integer> {

    Optional<Fruit> findByName(String name);
}