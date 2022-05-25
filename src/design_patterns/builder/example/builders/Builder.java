package design_patterns.builder.example.builders;

import design_patterns.builder.example.cars.CarType;
import design_patterns.builder.example.components.Engine;
import design_patterns.builder.example.components.GPSNavigator;
import design_patterns.builder.example.components.Transmission;
import design_patterns.builder.example.components.TripComputer;

/**
 * Интерфейс Строителя объявляет все возможные этапы и шаги конфигурации
 * продукта.
 */
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
