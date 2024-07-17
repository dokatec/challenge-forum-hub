package br.com.alura.forumhub.domain.topico;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(

        @NotNull String titulo,
        @NotNull String mensagem,
        @NotNull String curso,
        @NotNull String autor,
        LocalDateTime data

) {

}
