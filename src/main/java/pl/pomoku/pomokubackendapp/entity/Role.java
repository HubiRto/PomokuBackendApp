package pl.pomoku.pomokubackendapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
}
