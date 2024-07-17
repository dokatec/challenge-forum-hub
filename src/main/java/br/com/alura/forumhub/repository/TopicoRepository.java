package br.com.alura.forumhub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.forumhub.domain.topico.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.status = true")
    Page<Topico> findAllByAtivo(Pageable paginacao);

    @Query("SELECT t FROM Topico t WHERE t.status = false")
    Page<Topico> findAllByDesativado(Pageable paginacao);

}