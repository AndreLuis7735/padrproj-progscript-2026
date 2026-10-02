package br.edu.fatecfranca.api.states;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.CarStatus;
import br.edu.fatecfranca.api.entities.Customer;

public class AvailableState implements CarState {

    @Override
    public void reserve(Car car) {
        car.setStatus(CarStatus.RESERVED);
    }

    @Override
    public void sell(Car car, Customer customer, BigDecimal price) {
        car.setCustomer(customer);
        car.setSellingPrice(price);
        car.setSellingDate(LocalDate.now());
        car.setStatus(CarStatus.SOLD);
    }

    @Override
    public void cancelReservation(Car car) {
        throw new IllegalStateException(
                "Carro " + car.getPlates() + " já está disponível, não há reserva para cancelar.");
    }
}
