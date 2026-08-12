package br.com.siglab.API_siglab.domain.solicitacao;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    private UUID id;
    private UUID usuarioId;
    
    
}
