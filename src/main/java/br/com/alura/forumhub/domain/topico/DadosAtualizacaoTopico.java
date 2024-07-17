package br.com.alura.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosAtualizacaoTopico(
                Long id,
                String titulo,
                String mensagem,
                String curso,
                String autor,
                LocalDateTime data) {

        public DadosAtualizacaoTopico(Topico topico) {
                this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.getAutor(),
                                topico.getData());

        }
}
