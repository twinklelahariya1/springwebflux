package com.reactivejavaexample.learnrj.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Fruit {

    @Id
    private String fruitId;
    private String fruitName;
    private int fruitCostPerKG;
}
