package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User salomao = new User();
		salomao.setName("Salomão Ferreira");
		salomao.setGender("M");
		salomao.setEmail("salomao@gmail.com");

		User leonardo = new User();
		leonardo.setName("Leonardo Paiva");
		leonardo.setGender("M");
		leonardo.setEmail("leonardo@gmail.com");

		userRepository.save(salomao);
		userRepository.save(leonardo);

	}

}
