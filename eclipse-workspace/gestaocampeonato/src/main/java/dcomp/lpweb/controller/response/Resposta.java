package dcomp.lpweb.controller.response;

import java.util.ArrayList;
import java.util.List;

public class Resposta<T> {

    private T dados;
    private List<Erro> erros = new ArrayList<Erro>();


    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public List<Erro> getErros() {
        return erros;
    }

    public void setErros(List<Erro> erros) {
        this.erros = erros;
    }

    public void adiciona(Erro erro) {
        erros.add(erro );
    }
}

