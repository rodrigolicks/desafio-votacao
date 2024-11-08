package br.net.licks.db.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Sessao {
	
	private static final int DURACAO_VOTACAO_PADRAO = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private LocalDateTime inicioVotacao;

    @Column(nullable = false)
    private LocalDateTime fimVotacao;
		
	private Integer duracaoVotacao;
	
	@JsonIgnore
	private Integer votosSim = 0;
	
	@JsonIgnore
	private Integer votosNao = 0;
	
	@JsonProperty("votos_sim")
	@JsonInclude
	public Integer getVotosSim() {
		if (isAberta()) return 0;
		return this.votosSim;
	}
	
	@JsonProperty("votos_nao")
	@JsonInclude
	public Integer getVotosNao() {
		if (isAberta()) return 0;
		return this.votosNao;
	}

	@Transient
	private boolean aberta;
	
	public void incrementarSim() {
		setVotosSim(getVotosSim() + 1);
	}
	
	public void incrementarNao() {
		setVotosNao(getVotosNao() + 1);
	}	
	
	@PostLoad
	protected void postLoad() {
		setAberta(LocalDateTime.now().isBefore(getFimVotacao()));
	}
	
    @PrePersist
    protected void prePersist() {
    	int duracaoVotacao = getDuracaoVotacao() == null ? DURACAO_VOTACAO_PADRAO : getDuracaoVotacao();
    	setInicioVotacao(LocalDateTime.now());
    	setFimVotacao(getInicioVotacao().plusMinutes(duracaoVotacao));
    }
}
