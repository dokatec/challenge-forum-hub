package br.com.alura.forumhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forumhub.domain.topico.DadosCadastroTopico;
import br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico;
import br.com.alura.forumhub.domain.topico.DadosListagemTopico;
import br.com.alura.forumhub.domain.topico.Topico;
import br.com.alura.forumhub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @SuppressWarnings("rawtypes")
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        var topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTodosTopicos(
            @PageableDefault(sort = { "title" }, size = 10) Pageable paginacao) {
        var topicos = repository.findAll(paginacao).map(DadosListagemTopico::new);

        return ResponseEntity.ok().body(topicos);
    }

}
