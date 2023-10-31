package com.med.voll.api.service.medico;


import com.med.voll.api.dto.telefone.AtualizaTelefoneDTO;
import com.med.voll.api.dto.medico.AtualizaMedicoDTO;
import com.med.voll.api.dto.medico.CadastraMedicoDTO;
import com.med.voll.api.dto.medico.ListaMedicoDTO;
import com.med.voll.api.model.medico.Medico;
import com.med.voll.api.model.telefone.Telefone;
import com.med.voll.api.repository.medico.MedicoRepository;
import com.med.voll.api.service.telefone.TelefoneService;
import com.med.voll.api.service.endereco.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    public void cadastrar(CadastraMedicoDTO cadastraMedicoDTO) {
        Medico medico = build(cadastraMedicoDTO);
        medicoRepository.save(medico);
        telefoneService.cadastrar(cadastraMedicoDTO.getTelefones(), medico);
    }

    public AtualizaMedicoDTO atualizar(AtualizaMedicoDTO atualizaMedicoDTO) {
        Telefone telefone = telefoneService.atualizar(atualizaMedicoDTO.getTelefone());
        Medico medico = medicoRepository.findById(atualizaMedicoDTO.getId()).get();
        medico.setNome(atualizaMedicoDTO.getNome() != null ? atualizaMedicoDTO.getNome() : medico.getNome());
        medico.setEmail(atualizaMedicoDTO.getEmail() != null ? atualizaMedicoDTO.getEmail() : medico.getEmail());
        medicoRepository.save(medico);
        return AtualizaMedicoDTO.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .email(medico.getEmail())
                .telefone(
                        AtualizaTelefoneDTO.builder()
                                .ddd(telefone.getDdd())
                                .numero(telefone.getNumero())
                                .build())
                .build();
    }

    public void deletar(Long id) {
        Medico medico = medicoRepository.findById(id).get();
        medico.setAtivo(false);
        medicoRepository.save(medico);
    }

    public Page<ListaMedicoDTO> findMedicos(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(ListaMedicoDTO::new);
    }

    private Medico build(CadastraMedicoDTO cadastraMedicoDTO) {
        return Medico.builder()
                .nome(cadastraMedicoDTO.getNome())
                .email(cadastraMedicoDTO.getEmail())
                .crm(cadastraMedicoDTO.getCrm())
                .endereco(enderecoService.cadastrar(cadastraMedicoDTO.getEndereco()))
                .especialidade(cadastraMedicoDTO.getEspecialidade())
                .build();
    }
}
