package dcomp.lpweb.controller;


import dcomp.lpweb.Service.CampeonatoService;
import dcomp.lpweb.controller.dto.dto.CampeonatoDTO;
import dcomp.lpweb.model.Campeonato;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    @Autowired
    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }


    @GetMapping
    public List<Campeonato> todas() {
        return campeonatoService.todosOsCampeonatos();
    }


    @PostMapping
    public ResponseEntity<CampeonatoDTO> salva(@RequestBody CampeonatoDTO campeonatoDTO ) {

        Campeonato campeonato = new Campeonato();
        BeanUtils.copyProperties(campeonatoDTO, campeonato);

       Campeonato campeonatoSalvo = campeonatoService.salva(campeonato );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(campeonatoSalvo.getId())
                .toUri();

        BeanUtils.copyProperties(campeonatoSalvo, campeonatoDTO);

        return ResponseEntity.created(uri).body(campeonatoDTO);
    }

    @GetMapping("/{id}")
    public Campeonato buscaPor(@PathVariable Integer id) {
        return campeonatoService.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        campeonatoService.excluiPor(id );
    }


    @PutMapping("/{id}")
    public Campeonato altera(@PathVariable  Integer id, @RequestBody Campeonato campeonato) {
        return  campeonatoService.atualiza(id, campeonato );
    }

}

