package com.example.Securityprueba.entities.candidatesModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "representative")
public class Representative extends Candidates{
}
