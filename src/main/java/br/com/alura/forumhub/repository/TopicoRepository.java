package br.com.alura.forumhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forumhub.domain.topico.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}