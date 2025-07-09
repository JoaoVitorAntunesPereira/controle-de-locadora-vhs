package br.edu.ifpr.foz.controle_de_locadora_vhs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Genre;
import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.Status;
import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.GenreRepository;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.UserRepository;
import br.edu.ifpr.foz.controle_de_locadora_vhs.repositories.VHSRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false) 
class ControleDeLocadoraVhsApplicationTests {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	VHSRepository vhsRepository;

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void deveInserirUmGenero(){

		Genre genero = new Genre();
		genero.setDescription("Terror");

		entityManager.persist(genero);

		System.out.println("Id do genero " + genero.getDescription() + ": " +genero.getId());
	}

	@Test
	void deveInserirUmVhs(){

		List<Genre> generos = new ArrayList<>();

		generos = genreRepository.findAll();

		VHS vhs = new VHS();
		vhs.setTitle("TEstde vhs");
		vhs.setDirector("NInguem");
		vhs.setGenre(generos.get(0));
		vhs.setStatus(Status.DISPONIVEL);
		vhs.setRegistrationDate(LocalDate.now());

		vhsRepository.save(vhs);
	}

	@Test
	void deveInserirUmUsuario(){

		User user = new User();

		user.setEmail("admin@gmail.com");
		user.setName("admin");
		user.setPassword("admin");

		User userAdicionado = userRepository.save(user);

		assertNotNull(userAdicionado.getId());

	}

	@Test
	@Rollback(true)
	void naoDeveRegistrarUsuarioComEmailJaCadastrado(){

		User user = new User();

		user.setName("Joao");
		user.setEmail("admin@gmail.com");
		user.setPassword("1234");

		assertThrows(DataIntegrityViolationException.class, () -> {
			userRepository.save(user);
		});
	}


}
