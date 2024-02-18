package br.com.alura.roger.fipetable.business;

import br.com.alura.roger.fipetable.model.record.CarRecord;
import br.com.alura.roger.fipetable.model.record.ModelsYearsRecord;
import br.com.alura.roger.fipetable.model.record.YearRecord;
import br.com.alura.roger.fipetable.service.BrandService;
import br.com.alura.roger.fipetable.service.ConsumerApi;
import br.com.alura.roger.fipetable.util.constant.Constant;
import br.com.alura.roger.fipetable.util.converter.ConvertData;
import br.com.alura.roger.fipetable.util.enums.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CarBusiness {
    @Autowired
    private ConsumerApi consumerApi;
    @Autowired
    private BrandService brandService;

    @Autowired
    private ConvertData convertData;

    @Value("${fipetable.apirest}")
    private String Url;

    public List<CarRecord> getCars(String model, ModelsYearsRecord models, String brandCarCode) {
        List<CarRecord> cars = new ArrayList<>();
        models.models().forEach(m -> {
            if (m.name().toUpperCase().contains(model.toUpperCase())) {
                var retApi = consumerApi.getData(Url + "/" + Vehicle.CARROS.toString().toLowerCase() + "/" + Constant.Brand + "/" + brandCarCode + "/" + Constant.Model + "/" + m.code() + "/" + Constant.Year);
                var years = convertData.convertDataList(retApi, YearRecord.class);
                years.forEach(y -> {
                    var car = consumerApi.getData(Url + Vehicle.CARROS.toString().toLowerCase() + Constant.Brand + brandCarCode + Constant.Model + m.code() + Constant.Year + y.code());
                    var carRecord = convertData.convertData(car, CarRecord.class);
                    cars.add(carRecord);
                });
            }
        });
        return cars;
    }
}
