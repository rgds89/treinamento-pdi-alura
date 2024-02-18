package br.com.alura.roger.fipetable.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CarRecord(@JsonAlias("TipoVeiculo") Integer vehicleType,
                        @JsonAlias("Marca") String brand,
                        @JsonAlias("Modelo") String model,
                        @JsonAlias("AnoModelo") String year,
                        @JsonAlias("Valor") String value,
                        @JsonAlias("Combustivel") String fuel,
                        @JsonAlias("CodigoFipe") String fipeCode,
                        @JsonAlias("MesReferencia") String referenceMonth,
                        @JsonAlias("SiglaCombustivel") String fuelInitials) {
}
