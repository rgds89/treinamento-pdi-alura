package br.com.alura.roger.fipetable.service;

import br.com.alura.roger.fipetable.business.CarBusiness;
import br.com.alura.roger.fipetable.model.record.CarRecord;
import br.com.alura.roger.fipetable.model.record.ModelsYearsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarBusiness carBusiness;
    public List<CarRecord> getCars(String model, ModelsYearsRecord models, String brandCarCode) {
        return carBusiness.getCars(model, models, brandCarCode);
    }
}
