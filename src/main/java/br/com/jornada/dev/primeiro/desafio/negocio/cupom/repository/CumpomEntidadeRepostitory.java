package br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.CupomEntidade;

@Repository
public interface CumpomEntidadeRepostitory extends JpaRepository<CupomEntidade, Long>{

}
