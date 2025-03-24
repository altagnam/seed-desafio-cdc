package br.com.jornada.dev.primeiro.desafio.autor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jornada.dev.primeiro.desafio.model.CategoriaRequest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoriaTest {
	
	
	@Test
	@DisplayName("verificar se é possível criar uma CategoriaEntidade a partir de um CategoriaRequest")
	void validarCriacaoInstanciaEntidadeSucesso() {		
		var request = new CategoriaRequest("Java dev");	    
		assertNotNull(request.toEntidade());		
	}
	
	@Test
	@DisplayName("verificar se o nome esta diferente de null")
	void validarCriacaoInstanciaEntidadeComErroNomeNull() {		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			var request = new CategoriaRequest(null);
			request.toEntidade();
	    });		
		assertTrue("Nome é obrigatório".contains(exception.getMessage()));		
	}
	
	@Test
	@DisplayName("verificar se o nome esta diferente de vazio")
	void validarCriacaoInstanciaEntidadeComErroNomeEmpty() {		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			var request = new CategoriaRequest("");
			request.toEntidade();
	    });		
		assertTrue("Nome é obrigatório".contains(exception.getMessage()));		
	}	
	
}
