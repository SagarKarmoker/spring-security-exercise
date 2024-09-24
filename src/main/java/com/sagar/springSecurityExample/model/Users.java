package com.sagar.springSecurityExample.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Entity
@Data
public class Users {

    @jakarta.persistence.Id
    @Id
    private int id;
    private String username;
    private String password;
}
