package br.com.alura.forumhub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(

        @NotNull String titulo,
        @NotNull String mensagem,
        @NotNull String curso,
        @NotNull String nome

) {

}
