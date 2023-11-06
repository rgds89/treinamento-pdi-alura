package com.med.voll.api.service.paciente;

import com.med.voll.api.dto.paciente.AtualizaPacienteDTO;
import com.med.voll.api.dto.paciente.CadastraPacienteDTO;
import com.med.voll.api.dto.paciente.DetalhamentoPacienteDTO;
import com.med.voll.api.dto.paciente.ListaPacienteDTO;
import com.med.voll.api.model.paciente.Paciente;
import com.med.voll.api.repository.paciente.PacienteRepositoy;
import com.med.voll.api.service.endereco.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepositoy pacienteRepositoy;
    private final EnderecoService enderecoService;

    public DetalhamentoPacienteDTO cadastrar(CadastraPacienteDTO cadastraPacienteDTO) {
        Paciente paciente = pacienteRepositoy.save(build(cadastraPacienteDTO));
        return DetalhamentoPacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .email(paciente.getEmail())
                .endereco(paciente.getEndereco())
                .build();
    }

    public DetalhamentoPacienteDTO atualizar(AtualizaPacienteDTO atualizaPacienteDTO) {
        Paciente paciente = pacienteRepositoy.findById(atualizaPacienteDTO.getId()).get();
        if (atualizaPacienteDTO.getEndereco() != null) {
            enderecoService.atualizar(atualizaPacienteDTO.getEndereco());        }
        paciente.setNome(atualizaPacienteDTO.getNome() != null ? atualizaPacienteDTO.getNome() : paciente.getNome());
        paciente.setTelefone(atualizaPacienteDTO.getTelefone() != null ? atualizaPacienteDTO.getTelefone() : paciente.getTelefone());
        pacienteRepositoy.save(paciente);
        return DetalhamentoPacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .email(paciente.getEmail())
                .endereco(paciente.getEndereco())
                .build();
    }

    public void deletar(Long id) {
        Paciente paciente = pacienteRepositoy.findById(id).get();
        paciente.setAtivo(false);
        pacienteRepositoy.save(paciente);
    }

    public Page<ListaPacienteDTO> listar(Pageable paginacao) {
        return pacienteRepositoy.findAllByAtivoTrue(paginacao).map(ListaPacienteDTO::new);
    }

    public DetalhamentoPacienteDTO findPaciente(Long id){
        Paciente paciente = pacienteRepositoy.findById(id).get();
        return DetalhamentoPacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .email(paciente.getEmail())
                .endereco(paciente.getEndereco())
                .build();
    }

    private Paciente build(CadastraPacienteDTO cadastraPacienteDTO) {
        return Paciente.builder()
                .nome(cadastraPacienteDTO.getNome())
                .email(cadastraPacienteDTO.getEmail())
                .cpf(cadastraPacienteDTO.getCpf())
                .telefone(cadastraPacienteDTO.getTelefone())
                .endereco(enderecoService.cadastrar(cadastraPacienteDTO.getEndereco()))
                .build();
    }
}
