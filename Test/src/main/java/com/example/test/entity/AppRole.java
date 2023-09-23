package com.example.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    /*@ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> appUsers;*/
}
