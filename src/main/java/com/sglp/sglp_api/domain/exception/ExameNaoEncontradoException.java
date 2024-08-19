package com.sglp.sglp_api.domain.exception;

public class ExameNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ExameNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public ExameNaoEncontradoException(String mensagem, String exameId) {
        this(String.format("Não existe exame da matéria com o código %d", exameId));
    }
}
