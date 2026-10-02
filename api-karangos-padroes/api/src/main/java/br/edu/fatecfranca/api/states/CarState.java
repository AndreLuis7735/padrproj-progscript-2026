package br.edu.fatecfranca.api.states;

import java.math.BigDecimal;

import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.Customer;


public interface CarState {

    void reserve(Car car);

    void sell(Car car, Customer customer, BigDecimal price);

    void cancelReservation(Car car);
}
