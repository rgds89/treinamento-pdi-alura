package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.AtualizarPagamentoDto;
import br.com.alurafood.pagamentos.dto.CadastrarPagamentoDto;
import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity<PagamentoDto> criarPagamento(@RequestBody @Valid CadastrarPagamentoDto dto, UriComponentsBuilder uriBuilder) {
        PagamentoDto pagamentoDto = pagamentoService.criarPagamento(dto);
        URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoDto.id()).toUri();
        return ResponseEntity.created(uri).body(pagamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizarPagamento(@PathVariable Long id, @RequestBody @Valid AtualizarPagamentoDto dto) {
        PagamentoDto pagamentoDto = pagamentoService.atualizarPagamento(id, dto);
        return ResponseEntity.ok(pagamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<PagamentoDto> listarPagamentos(@PageableDefault(size = 10) Pageable paginacao) {
        return pagamentoService.listarPagamentos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> buscarPagamento(@PathVariable @NotNull Long id) {
        PagamentoDto pagamentoDto = pagamentoService.buscarPagamento(id);
        return ResponseEntity.ok(pagamentoDto);
    }
}
