package cat.itacademy.s04.t02.n03.mvc.entities.fruit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(collection = "fruits")
public class Fruit {
    @Id
    private String id;
    private String name;
    private int quantityInKilos;
}
