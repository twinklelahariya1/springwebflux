package com.reactivejavaexample.learnrj.service;


import com.couchbase.client.java.error.DocumentAlreadyExistsException;
import com.couchbase.client.java.error.DocumentDoesNotExistException;
import com.reactivejavaexample.learnrj.model.Fruit;
import com.reactivejavaexample.learnrj.repository.FruitRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FruitService {


    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Mono<Fruit> create(Fruit fruit) {
        return fruitRepository.existsById(fruit.getFruitId())
                .flatMap(exists -> exists ? alreadyExists(fruit.getFruitId()) : fruitRepository.save(fruit));
    }

    public Flux<Fruit> findAllFruits(){
        return fruitRepository.findAll();
    }

    public Mono<Fruit> find(String id) {
        return fruitRepository.findById(id)
                .switchIfEmpty(doesNotExist(id));
    }

    public Mono<Fruit> update(String id, Fruit fruit) {
        return fruitRepository.existsById(id).flatMap(exists -> exists ?
                fruitRepository.save(new Fruit(id, fruit.getFruitName(), fruit.getFruitCostPerKG())) :
                doesNotExist(id));
    }

    public Mono<Void> delete(String id) {
        return fruitRepository.existsById(id)
                .flatMap(exists -> exists ? fruitRepository.deleteById(id) : doesNotExist(id));

    }

    private <T> Mono<T> doesNotExist(String id) {
        return Mono.error(new DocumentDoesNotExistException("Fruit with id " + id + " does not exist"));
    }

    private <T> Mono<T> alreadyExists(String id) {
        return Mono.error(new DocumentAlreadyExistsException("Fruit with id " + id + " already exists"));
    }
}
