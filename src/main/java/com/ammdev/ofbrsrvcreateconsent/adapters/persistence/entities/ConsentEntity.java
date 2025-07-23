package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "consent")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Column(name = "business_identification", length = 100)
    private String businessIdentification;

    @Column(name = "business_rel", length = 50)
    private String businessRel;

    @Column(name = "consent_id", nullable = false, length = 100)
    private String consentId;

    @Column(name = "creation_date_time", nullable = false, length = 30)
    private String creationDateTime;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "status_update_date_time", nullable = false, length = 30)
    private String statusUpdateDateTime;

    @Column(name = "expiration_date_time", length = 30)
    private String expirationDateTime;

    @ManyToMany
    @JoinTable(
        name = "consent_permission",
        joinColumns = @JoinColumn(name = "consent_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions;
}
