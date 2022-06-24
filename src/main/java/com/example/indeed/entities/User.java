package com.example.indeed.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(length = 50,nullable = false,unique = true)
    private String name;
    @Column(length = 50,nullable = false,unique = true)
    private String email;
    @Column(length = 12,nullable = false,unique = true)
    private String phone_number;
    @Column(length = 200)
    private String adress;
    @Column(nullable=false)
    private String password;
    private boolean active;
    private String role;



    @OneToMany(mappedBy = "poster")
    private List<Offre> list_offre= new ArrayList<Offre>();
}
