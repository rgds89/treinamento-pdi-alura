package br.com.alura.roger.fipetable.controller;

import br.com.alura.roger.fipetable.model.record.FipeRecord;
import br.com.alura.roger.fipetable.service.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fipe")
public class FipeController {
    @Autowired
    private FipeService fipeService;

    @GetMapping
    public ResponseEntity<?> getveiculos(@RequestBody FipeRecord fipeRecord) {
        return ResponseEntity.ok().body(fipeService.getVehicle(fipeRecord.vehicleType(), fipeRecord.brand(), fipeRecord.model()));
    }
}
