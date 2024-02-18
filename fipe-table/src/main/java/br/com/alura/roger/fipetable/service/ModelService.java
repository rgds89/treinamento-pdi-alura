package br.com.alura.roger.fipetable.service;

import br.com.alura.roger.fipetable.business.ModelBusiness;
import br.com.alura.roger.fipetable.model.record.ModelsYearsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    @Autowired
    private ModelBusiness modelBusiness;
    public ModelsYearsRecord getModels(String brandCode) {
        return modelBusiness.getModels(brandCode);
    }
}
