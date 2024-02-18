package br.com.alura.roger.fipetable.service;

import br.com.alura.roger.fipetable.business.BrandBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    private BrandBusiness brandBusiness;

    public String getBrandCarCode(String brand) {
        return brandBusiness.getBrandCarCode(brand);
    }
}
