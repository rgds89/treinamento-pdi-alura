package com.med.voll.api.service.address;

import com.med.voll.api.dto.address.UpdateAddressDTO;
import com.med.voll.api.dto.address.RegisterAddressDTO;
import com.med.voll.api.model.address.Address;
import com.med.voll.api.repository.address.AddressRepository;
import com.med.voll.api.repository.patient.PatientRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final PatientRepositoy patientRepositoy;

    public Address cadastrar(RegisterAddressDTO enderecoDTO){
        Address address = build(enderecoDTO);
        addressRepository.save(address);
        return address;
    }

    private Address build (RegisterAddressDTO enderecoDTO){
        return Address.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .uf(enderecoDTO.getUf())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public void atualizar(UpdateAddressDTO updateAddressDTO) {
        Address address = addressRepository.findById(updateAddressDTO.getId()).get();
        addressRepository.save(
                Address.builder()
                        .logradouro(updateAddressDTO.getLogradouro() != null ? updateAddressDTO.getLogradouro() : address.getLogradouro())
                        .numero(updateAddressDTO.getNumero() != null ? updateAddressDTO.getNumero() : address.getNumero())
                        .complemento(updateAddressDTO.getComplemento() != null ? updateAddressDTO.getComplemento(): address.getComplemento())
                        .bairro(updateAddressDTO.getBairro() != null ? updateAddressDTO.getBairro() : address.getBairro())
                        .cep(updateAddressDTO.getCep() != null ? updateAddressDTO.getCep() : address.getCep())
                        .cidade(updateAddressDTO.getCidade() != null ? updateAddressDTO.getCidade() : address.getCidade())
                        .uf(updateAddressDTO.getUf() != null ? updateAddressDTO.getUf() : address.getUf())
                        .build());
    }
}
