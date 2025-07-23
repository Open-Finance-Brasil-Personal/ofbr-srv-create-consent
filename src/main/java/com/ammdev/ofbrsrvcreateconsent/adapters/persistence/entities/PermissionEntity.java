package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permission")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;
}
