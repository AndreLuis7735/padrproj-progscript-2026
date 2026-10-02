package br.edu.fatecfranca.api.states;

import java.math.BigDecimal;

import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.Customer;


public class SoldState implements CarState {

    @Override
    public void reserve(Car car) {
        throw new IllegalStateException(
                "Carro " + car.getPlates() + " já foi vendido, não pode ser reservado.");
    }

    @Override
    public void sell(Car car, Customer customer, BigDecimal price) {
        throw new IllegalStateException(
                "Carro " + car.getPlates() + " já foi vendido.");
    }

    @Override
    public void cancelReservation(Car car) {
        throw new IllegalStateException(
                "Carro " + car.getPlates() + " já foi vendido, não há reserva para cancelar.");
    }
}
