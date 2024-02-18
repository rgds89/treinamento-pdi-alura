package br.com.alura.roger.fipetable.business;

import br.com.alura.roger.fipetable.model.record.ModelsYearsRecord;
import br.com.alura.roger.fipetable.service.ConsumerApi;
import br.com.alura.roger.fipetable.util.constant.Constant;
import br.com.alura.roger.fipetable.util.converter.ConvertData;
import br.com.alura.roger.fipetable.util.enums.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ModelBusiness {
    @Autowired
    private ConsumerApi consumerApi;

    @Value("${fipetable.apirest}")
    private String Url;

    @Autowired
    private ConvertData convertData;
    public ModelsYearsRecord getModels(String brandCode) {
        var retApi = consumerApi.getData(Url + "/" + Vehicle.CARROS.toString().toLowerCase() + "/" + Constant.Brand + "/" + brandCode + "/" + Constant.Model);
        return convertData.convertData(retApi, ModelsYearsRecord.class);
    }
}
