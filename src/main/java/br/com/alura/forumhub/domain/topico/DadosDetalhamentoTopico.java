package br.com.alura.forumhub.domain.topico;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        String curso,
        String nome) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.getNome());
    }

}
