package br.edu.fatecfranca.api.observers;

import org.springframework.stereotype.Component;

import br.edu.fatecfranca.api.audit.AuditLogger;
import br.edu.fatecfranca.api.entities.Car;

@Component
public class AuditLogObserver implements CarSaleObserver {

    @Override
    public void onCarSold(Car car) {
        AuditLogger.getInstance().log(
                "Carro " + car.getPlates() + " vendido por R$ " + car.getSellingPrice()
                        + " para o cliente de id " + car.getCustomerId());
    }
}
