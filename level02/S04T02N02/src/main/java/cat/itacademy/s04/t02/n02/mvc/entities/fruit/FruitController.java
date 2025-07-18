package cat.itacademy.s04.t02.n02.mvc.entities.fruit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/add")
    public ResponseEntity<Fruit> add(@RequestBody Fruit fruit) {
        return ResponseEntity.ok(fruitService.add(fruit));
    }

    @PutMapping("/update")
    public ResponseEntity<Fruit> update(@RequestBody Fruit fruit) {
        return ResponseEntity.ok(fruitService.update(fruit));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        fruitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getOneById(@PathVariable int id) {
        return ResponseEntity.ok(fruitService.findById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Fruit>> getAll() {
        return ResponseEntity.ok(fruitService.findAll());
    }
}