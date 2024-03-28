package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    Abrigo findByNome(String nome);

    boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email);

    Abrigo findByIdOrNome(Long id, String nome);
}
