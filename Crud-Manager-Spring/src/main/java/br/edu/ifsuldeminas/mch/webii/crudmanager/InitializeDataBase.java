package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Accommodation;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.TravelSite;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.TravelSiteRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private TravelSiteRepository travelSiteRepository;

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

		Address addressS = new Address();
		addressS.setPlace("Rua XYZ, Bairro ZZZ");
		addressS.setNumber(123);
		addressS.setZipCode("XXXXX-XXX");

		Address addressB = new Address();
		addressB.setPlace("Rua ZYX, Bairro CCC");
		addressB.setNumber(321);
		addressB.setZipCode("XXXXX-XXX");

		addressRepository.save(addressS);
		addressRepository.save(addressB);
		userRepository.flush();

		salomao.setAddress(addressS);
		leonardo.setAddress(addressB);

		TravelSite travelSite = new TravelSite();
		travelSite.setName("Trivago");
		travelSite.setUrl("trivago.com.br");
		travelSite.setCategory("Viagens, Turismo, Sexo");
		travelSite.setRating(4.7);

		Accommodation accommodation = new Accommodation();
		accommodation.setName("Casa na Praia Copacabana");
		accommodation.setPrice(124.43);
		
		userRepository.save(salomao);
		userRepository.save(leonardo);
		travelSiteRepository.save(travelSite);
		

	}

}
