package dcomp.lpweb.controller.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dcomp.lpweb.model.Campeonato;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CampeonatoDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nome;


    private Integer ano;

    private DTO<Campeonato, CampeonatoDTO> dto = new DTO<Campeonato, CampeonatoDTO>(this);

    public CampeonatoDTO() {  }

    public CampeonatoDTO(Campeonato campeonato) {
        this.comDadosDe(campeonato );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @JsonIgnore
    public Campeonato getCampeonato() {
        return dto.getEntity(new Campeonato() );
    }

    public CampeonatoDTO comDadosDe(Campeonato campeonato) {
        return dto.comDadosDe(campeonato);
    }

    public Campeonato atualizaIgnorandoNuloA(Campeonato campeonato) {
        return dto.mergeIgnorandoNulo(campeonato);
    }


    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ano='" + ano + '\'' +
                '}';
    }
}

