package br.edu.fatecfranca.api.services;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;


import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.CarStatus;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.observers.CarSaleObserver;
import br.edu.fatecfranca.api.repositories.CarRepository;
import br.edu.fatecfranca.api.repositories.CustomerRepository;
import br.edu.fatecfranca.api.states.AvailableState;
import br.edu.fatecfranca.api.states.CarState;
import br.edu.fatecfranca.api.states.ReservedState;
import br.edu.fatecfranca.api.states.SoldState;


@Service
public class CarService {


   private final CarRepository repository;
   private final CustomerRepository customerRepository;

   // Padrão Observer: lista de interessados em saber quando um carro é
   // vendido. CarService não conhece as classes concretas -- só a interface.
   private final List<CarSaleObserver> observers = new ArrayList<>();


   // O Spring injeta aqui todos os beans que implementam CarSaleObserver
   // (ex: AuditLogObserver) -- cada um é automaticamente inscrito como
   // observador, sem que CarService precise instanciá-los manualmente.
   public CarService(CarRepository repository,
                      CustomerRepository customerRepository,
                      List<CarSaleObserver> observers) {
       this.repository = repository;
       this.customerRepository = customerRepository;
       this.observers.addAll(observers);
   }


   public void addObserver(CarSaleObserver observer) {
       observers.add(observer);
   }


   // Padrão State: traduz o status atual (enum persistido) para o objeto
   // de estado correspondente, que sabe quais transições são válidas.
   private CarState resolveState(CarStatus status) {
       return switch (status) {
           case AVAILABLE -> new AvailableState();
           case RESERVED -> new ReservedState();
           case SOLD -> new SoldState();
       };
   }


   public Car reserve(Long carId) {
       Car car = repository.findById(carId)
               .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado: " + carId));

       resolveState(car.getStatus()).reserve(car);
       return repository.save(car);
   }


   public Car cancelReservation(Long carId) {
       Car car = repository.findById(carId)
               .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado: " + carId));

       resolveState(car.getStatus()).cancelReservation(car);
       return repository.save(car);
   }


   public Car sellCar(Long carId, Long customerId, BigDecimal price) {
       Car car = repository.findById(carId)
               .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado: " + carId));

       Customer customer = customerRepository.findById(customerId)
               .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + customerId));

       resolveState(car.getStatus()).sell(car, customer, price);
       Car savedCar = repository.save(car);

       // Dispara a notificação para todos os observadores inscritos
       // (ex: AuditLogObserver), sem que CarService precise conhecê-los.
       observers.forEach(observer -> observer.onCarSold(savedCar));

       return savedCar;
   }


   public Car create(Car car) {
       return repository.save(car);
   }


   public List<Car> findAll() {
       return repository.findAll();
   }


   public Optional<Car> findById(Long id) {
       return repository.findById(id);
   }

   public Car save(Car car) {
         return repository.save(car);
   }


   public Car update(Car car) {
       return repository.save(car);
   }


   public boolean existsById(Long id) {
       return repository.existsById(id);
   }


   public void deleteById(Long id) {
       repository.deleteById(id);
   }


}
