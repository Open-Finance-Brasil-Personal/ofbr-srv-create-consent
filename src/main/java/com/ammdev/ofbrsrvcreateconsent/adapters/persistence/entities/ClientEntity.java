package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String identification;

    @Column(nullable = false, length = 50)
    private String rel;
}
