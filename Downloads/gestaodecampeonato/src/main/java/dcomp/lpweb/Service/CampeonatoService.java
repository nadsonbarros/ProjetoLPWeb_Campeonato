package dcomp.lpweb.Service;



import dcomp.lpweb.model.Campeonato;
import dcomp.lpweb.repository.CampeonatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CampeonatoService {
    private final CampeonatoRepository campeonatoRepository;

    @Autowired
    public CampeonatoService(CampeonatoRepository campeonatoRepository) {

        this.campeonatoRepository = campeonatoRepository;
    }

    @Transactional(readOnly = true)
    public List<Campeonato> todosOsCampeonatos() {
        return campeonatoRepository.findAll();
    }

    @Transactional
    public Campeonato salva(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato );

    }

    @Transactional(readOnly = true)
    public Campeonato buscaPor(Integer id) {
        return campeonatoRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        campeonatoRepository.deleteById(id );
    }

    @Transactional
    public Campeonato atualiza(Integer id, Campeonato campeonato) {

        Campeonato campeonatoSalva = this.buscaPor(id);
        BeanUtils.copyProperties(campeonato, campeonatoSalva, "id");

        return  campeonatoSalva;
    }
}



