package dcomp.lpweb.controller;

import dcomp.lpweb.controller.dto.CampeonatoDTO;
import dcomp.lpweb.controller.response.Erro;
import dcomp.lpweb.controller.response.Resposta;
import dcomp.lpweb.controller.validation.Validacao;
import dcomp.lpweb.model.Campeonato;
import dcomp.lpweb.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    @Autowired
    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping
    public Resposta<List<CampeonatoDTO>> todas() {

        List<Campeonato> campeonatos = campeonatoService.todosOsCampeonatos();
        List<CampeonatoDTO> campeonatosDTO = new ArrayList<CampeonatoDTO>(campeonatos.size());

        campeonatos.forEach(campeonato -> campeonatosDTO.add(new CampeonatoDTO(campeonato) ));

        Resposta<List<CampeonatoDTO>> resposta = new Resposta<List<CampeonatoDTO>>();
        resposta.setDados(campeonatosDTO);

        return resposta;
    }


    @PostMapping
    public ResponseEntity<Resposta<CampeonatoDTO>> salva(@Valid @RequestBody CampeonatoDTO campeonatoDTO) {

        Campeonato campeonatoSalva = campeonatoService.salva(campeonatoDTO.getCampeonato());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(campeonatoSalva.getId())
                .toUri();

        Resposta<CampeonatoDTO> resposta = new Resposta<CampeonatoDTO>();
        resposta.setDados(campeonatoDTO.comDadosDe(campeonatoSalva));

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping("/{id}")
    public Resposta<CampeonatoDTO> buscaPor(@PathVariable Integer id) {

        Campeonato campeonato = campeonatoService.buscaPor(id);

        Resposta<CampeonatoDTO> resposta = new Resposta<CampeonatoDTO>();
        resposta.setDados(new CampeonatoDTO(campeonato ) );

        return resposta;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        campeonatoService.excluiPor(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Resposta<CampeonatoDTO>> atualizar(@PathVariable Integer id,
                                                            @RequestBody CampeonatoDTO campeonatoDTO) {

        Campeonato campeonato = campeonatoDTO.atualizaIgnorandoNuloA(campeonatoService.buscaPor(id));

        Resposta<CampeonatoDTO> resposta = new Resposta<CampeonatoDTO>();

        Validacao<CampeonatoDTO> validacao = new Validacao<CampeonatoDTO>();
        List<Erro> erros =  validacao.valida(campeonatoDTO.comDadosDe(campeonato) );

        if (Objects.nonNull( erros ) &&  !erros.isEmpty() ) {
            resposta.setErros(erros );
            return ResponseEntity.badRequest().body(resposta );
        }

        Campeonato campeonatoAtualizada = campeonatoService.atualiza(id, campeonato);
        resposta.setDados(new CampeonatoDTO(campeonatoAtualizada ));

        return ResponseEntity.ok(resposta);

    }
}

