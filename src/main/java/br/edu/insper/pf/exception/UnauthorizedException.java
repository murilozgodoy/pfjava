package br.edu.insper.pf.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String mensagem) {
        super(mensagem);
    }
}