package org.example.taskmanagementsystem.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
public class User extends BaseModel{

//    private String name;
//    private String email;
    @Column(unique = true)
    private String username;
    private String password;

}
