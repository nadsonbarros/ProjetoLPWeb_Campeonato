
package dcomp.lpweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Campeonato")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ano;

    private String nome;


    @Column(name = "momento_criacao")
    private LocalDateTime momentoCriacao;

    @PrePersist
    private void persist() {
        this.momentoCriacao = LocalDateTime.now();
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "campeonatos")
    private final List<Campeonato> campeonatos = new ArrayList<>();


    public Campeonato() {
    }

    public Campeonato(Integer ano, String nome) {
        this.ano = ano;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Campeonato> getCampeonatos() {
        return campeonatos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato campeonato = (Campeonato) o;
        return Objects.equals(id, campeonato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public LocalDateTime getMomentoCriacao() {
        return momentoCriacao;
    }

    @Override
    public String toString() {
        return "Campeonato{" + "id=" + id + ", nome='" + nome + '\'' + ", ano='" + ano + '\'' + '}';
    }

}


