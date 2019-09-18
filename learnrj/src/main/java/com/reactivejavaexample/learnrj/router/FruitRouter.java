package com.reactivejavaexample.learnrj.router;


import com.reactivejavaexample.learnrj.handler.FruitHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class FruitRouter {


    @Bean
    public RouterFunction<ServerResponse> route(FruitHandler handler) {
        return RouterFunctions
                .route(GET("/fruits").and(accept(MediaType.APPLICATION_JSON)), handler::getAllFruits)
                .andRoute(POST("/fruits").and(accept(MediaType.APPLICATION_JSON)), handler::createFruit)
                .andRoute(GET("/persons/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getFruit)
                .andRoute(PUT("/persons/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::updateFruit)
                .andRoute(DELETE("/persons/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deletePerson);
    }

}
