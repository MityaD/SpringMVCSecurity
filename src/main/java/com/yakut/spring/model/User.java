package com.yakut.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity                                                             //сущность
@Getter
@Setter
@RequiredArgsConstructor                                            //требуемый конструктор аргументов
@Table(name = "users")                                              //таблица
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)             //генерируемое значение (стратегия = тип поколения. личность
    @Column(name = "id")                                            //столбец
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)      //один к одному (каскад = каскадный тип.все, удаление сирот = правда)
    @JoinColumn (name = "id_address")                               //присоединить столбец
    private Address address;
}
