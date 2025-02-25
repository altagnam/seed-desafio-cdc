package br.com.jornada.dev.primeiro.desafio.autor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jornada.dev.primeiro.desafio.model.AutorRequest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AutorTest {
	
	
	@Test
	void validarInstanciaAutorEntidadeSucesso() {		
		var autor = new AutorRequest("João", "Teste do João", "joao@teste.com.br");	    
		assertNotNull(autor.toEntidade());		
	}
	
	@Test
	void validarNomePreenchido() {		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new AutorRequest(null, "Teste de João", "joao@teste.com.br");
	    });		
		assertTrue("Nome é obrigatório".contains(exception.getMessage()));		
	}
	
	@Test
	void validarDescricaoPreenchido() {		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new AutorRequest("João", "", "joao@teste.com.br");
	    });		
		assertTrue("Descrição é obrigatório".contains(exception.getMessage()));		
	}
	
	@Test
	@DisplayName("verificar se email foi preenchido")
	void validarEmailPreenchido() {		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new AutorRequest("João", "Teste de João", "");
	    });		
		assertTrue("E-mail é obrigatório".contains(exception.getMessage()));		
	}
	
	@Test
	@DisplayName("verificar se email é valido")
	void validarEmailInvalido() {		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new AutorRequest("João", "Teste de João", "teste.com.br");
	    });		
		assertTrue("E-mail inválido".contains(exception.getMessage()));		
	}

}
