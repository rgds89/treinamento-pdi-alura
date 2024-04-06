package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.AtualizarPagamentoDto;
import br.com.alurafood.pagamentos.dto.CadastrarPagamentoDto;
import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.exception.PagamentoException;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.enums.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoDto criarPagamento(CadastrarPagamentoDto dto) {
        Pagamento pagamento = buildPagamento(dto);
        pagamentoRepository.save(pagamento);
        return buildPagamentoDto(pagamento);
    }

    public PagamentoDto atualizarPagamento(Long id, AtualizarPagamentoDto dto) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoException("Pagamento não encontrado"));
        pagamento.builder()
                .valor(dto.valor() == null ? pagamento.getValor() : dto.valor())
                .nome(dto.nome() == null ? pagamento.getNome() : dto.nome())
                .numero(dto.numero() == null ? pagamento.getNumero() : dto.numero())
                .expiracao(dto.expiracao() == null ? pagamento.getExpiracao() : dto.expiracao())
                .codigo(dto.codigo() == null ? pagamento.getCodigo() : dto.codigo())
                .status(dto.status() == null ? pagamento.getStatus() : dto.status())
                .build();
        pagamentoRepository.save(pagamento);
        return buildPagamentoDto(pagamento);
    }

    public void deletarPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public Page<PagamentoDto> listarPagamentos(Pageable paginacao) {
        return pagamentoRepository.findAll(paginacao)
                .map(this::buildPagamentoDto);
    }

    public PagamentoDto buscarPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoException("Pagamento não encontrado"));
        return buildPagamentoDto(pagamento);
    }

    private PagamentoDto buildPagamentoDto(Pagamento pagamento) {
        return PagamentoDto.builder()
                .id(pagamento.getId())
                .valor(pagamento.getValor())
                .nome(pagamento.getNome())
                .numero(pagamento.getNumero())
                .expiracao(pagamento.getExpiracao())
                .codigo(pagamento.getCodigo())
                .status(pagamento.getStatus())
                .pedidoId(pagamento.getPedidoId())
                .formaDePagamentoId(pagamento.getFormaDePagamentoId())
                .build();
    }

    private Pagamento buildPagamento(CadastrarPagamentoDto dto) {
        return Pagamento.builder()
                .valor(dto.valor())
                .nome(dto.nome())
                .numero(dto.numero())
                .expiracao(dto.expiracao())
                .codigo(dto.codigo())
                .status(Status.CRIADO)
                .pedidoId(dto.pedidoId())
                .formaDePagamentoId(dto.formaDePagamentoId())
                .build();
    }
}
