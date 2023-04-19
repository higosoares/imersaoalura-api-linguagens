package br.com.alura.linguagensapi;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {
    List<Linguagem> findByOrderByRanking();
}
