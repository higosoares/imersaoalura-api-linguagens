package br.com.alura.linguagensapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LinguagemController {

//    private List<Linguagem> linguagens =
//            List.of(
//                    new Linguagem("Java", "https://github.com/abrahamcalf/programming-languages-logos/blob/master/src/java/java_128x128.png", 1),
//                    new Linguagem("PHP", "https://github.com/abrahamcalf/programming-languages-logos/blob/master/src/php/php_128x128.png", 1)
//            );

    @Autowired
    private LinguagemRepository repository;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repository.findByOrderByRanking();
        return linguagens;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repository.save(linguagem);
        return new ResponseEntity<>(linguagemSalva, HttpStatus.CREATED);
    }

    @GetMapping(value="/linguagens/{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value="/linguagens/{id}")
    public Linguagem atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        Linguagem linguagemAtual = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        linguagemAtual.setTitle(linguagem.getTitle());
        linguagemAtual.setImage(linguagem.getImage());
        linguagemAtual.setRanking(linguagem.getRanking());

        Linguagem linguagemSalva = repository.save(linguagemAtual);
        return linguagemSalva;
    }

    @DeleteMapping(value="/linguagens/{id}")
    public void excluirLinguagem(@PathVariable String id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteById(id);
    }
}
