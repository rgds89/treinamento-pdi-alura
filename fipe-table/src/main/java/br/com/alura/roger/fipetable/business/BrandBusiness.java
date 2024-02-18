package br.com.alura.roger.fipetable.business;

import br.com.alura.roger.fipetable.model.record.BrandRecord;
import br.com.alura.roger.fipetable.service.ConsumerApi;
import br.com.alura.roger.fipetable.util.constant.Constant;
import br.com.alura.roger.fipetable.util.converter.ConvertData;
import br.com.alura.roger.fipetable.util.enums.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BrandBusiness {
    @Autowired
    private ConsumerApi consumerApi;

    @Value("${fipetable.apirest}")
    private String Url;

    @Autowired
    private ConvertData convertData;

    public String getBrandCarCode(String brand) {
        var brands = consumerApi.getData(Url + "/" + Vehicle.CARROS.toString().toLowerCase() + "/" + Constant.Brand);
        var brandRecords = convertData.convertDataList(brands, BrandRecord.class);
        return brandRecords.stream().filter(b -> b.name().equalsIgnoreCase(brand)).findFirst().get().code();
    }
}
