import entity.Car;
import jakarta.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
