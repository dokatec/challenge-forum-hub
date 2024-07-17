package br.com.alura.forumhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forumhub.domain.topico.DadosAtualizacaoTopico;
import br.com.alura.forumhub.domain.topico.DadosCadastroTopico;
import br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico;
import br.com.alura.forumhub.domain.topico.DadosListagemTopico;
import br.com.alura.forumhub.domain.topico.Topico;
import br.com.alura.forumhub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @SuppressWarnings("rawtypes")
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados) {
        var topico = new Topico(dados);
        repository.save(topico);

        return ResponseEntity.ok().body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTodosTopicos(
            @PageableDefault(sort = { "data" }, size = 10) Pageable paginacao) {
        var topicos = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok().body(topicos);
    }

    @GetMapping("/ativo")
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicosAtivos(
            @PageableDefault(sort = { "data" }, size = 10) Pageable paginacao) {
        var topicos = repository.findAllByAtivo(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok().body(topicos);
    }

    @GetMapping("/encerrado")
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicosEncerrados(
            @PageableDefault(sort = { "data" }, size = 10) Pageable paginacao) {
        var topicos = repository.findAllByDesativado(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok().body(topicos);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@RequestBody DadosAtualizacaoTopico dados, @PathVariable Long id) {
        var topico = repository.findById(id);
        topico.get().atualizarInformacoes(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico.get()));
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity encerrarTopico(@PathVariable Long id) {
        var topico = repository.findById(id);
        if (topico.isPresent()) {
            topico.get().inativo(false);
        }
        repository.save(topico.get());
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico.get()));
    }

}
