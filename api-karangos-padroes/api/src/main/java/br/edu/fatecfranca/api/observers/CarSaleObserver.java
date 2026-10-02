package br.edu.fatecfranca.api.observers;

import br.edu.fatecfranca.api.entities.Car;

public interface CarSaleObserver {
    void onCarSold(Car car);
}
