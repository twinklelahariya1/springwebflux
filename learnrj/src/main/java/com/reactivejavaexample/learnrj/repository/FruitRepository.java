package com.reactivejavaexample.learnrj.repository;


import com.reactivejavaexample.learnrj.model.Fruit;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends ReactiveCouchbaseRepository<Fruit,String> {

}
