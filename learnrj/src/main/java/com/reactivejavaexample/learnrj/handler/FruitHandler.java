package com.reactivejavaexample.learnrj.handler;


import com.reactivejavaexample.learnrj.model.Fruit;
import com.reactivejavaexample.learnrj.service.FruitService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FruitHandler {

    private final FruitService fruitService;

    public FruitHandler(FruitService fruitService) {
        this.fruitService = fruitService;
    }


    public Mono<ServerResponse> getAllFruits(ServerRequest request) {
        return fruitService
                .findAllFruits()
                .collectList()
                .flatMap(p -> ServerResponse.ok().syncBody(p));
    }

    public Mono<ServerResponse> createFruit(ServerRequest request) {

        return request.bodyToMono(Fruit.class).flatMap(fruitService::create).flatMap(fruit -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(fruit)));
    }

    public Mono<ServerResponse> getFruit(ServerRequest request) {

        return fruitService
                .find(request.pathVariable("id"))
                .flatMap(p -> ServerResponse.ok().syncBody(p));
    }

    public Mono<ServerResponse> updateFruit(ServerRequest request) {

        return request.bodyToMono(Fruit.class).flatMap(fruit -> fruitService.update(request.pathVariable("id"), fruit))
                .flatMap(fruit -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(fruit)));

//        return requestHandler.requireValidBody(
//                body -> {
//                    return body
//                            .flatMap(p -> personService.update(request.pathVariable("id"), p))
//                            .flatMap(p -> ServerResponse.ok().build());
//                }, request, Person.class);
    }

    public Mono<ServerResponse> deletePerson(ServerRequest request) {

        return fruitService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }
}
