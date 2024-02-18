package br.com.alura.roger.fipetable.service;

import br.com.alura.roger.fipetable.util.enums.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FipeService {
    @Autowired
    private CarService carService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;

    List<?> vehicles = new ArrayList<>();
    public List<?> getVehicle(String vehicleType, String brand, String model) {
        if(Vehicle.CARROS.toString().toUpperCase().equals(vehicleType.toUpperCase())){
            var brandCarCode = brandService.getBrandCarCode(brand);
            var models = modelService.getModels(brandCarCode);
            vehicles = carService.getCars(model, models, brandCarCode);
        } else if (Vehicle.MOTOS.toString().toUpperCase().equals(vehicleType.toUpperCase())) {

        } else if (Vehicle.CAMINHOES.toString().toUpperCase().equals(vehicleType.toUpperCase())) {

        }
        return vehicles;
    }
}
