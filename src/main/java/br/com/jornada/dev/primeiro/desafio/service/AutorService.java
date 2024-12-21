package br.com.jornada.dev.primeiro.desafio.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.Autor;
import br.com.jornada.dev.primeiro.desafio.model.AutorCadastrado;
import br.com.jornada.dev.primeiro.desafio.repository.AutorRepositorio;
import jakarta.validation.Valid;

@Service
public class AutorService {
	
	private final AutorRepositorio repositorio;
	public AutorService(final AutorRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	
	public AutorCadastrado cadastrar(final @Valid Autor novoAutor) {
		
		try {
			
			var autorRegitrado = this.repositorio.save(novoAutor.toEntidade());
			return autorRegitrado.toAutorCadastrado();
		
		}catch (Exception e) {
			Logger.getGlobal().log(Level.WARNING, e.getMessage(), e);
			throw e;
		}
		
	}
	

}
