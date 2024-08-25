package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Accommodation;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.TravelSite;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AccommodationRepository;
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

	@Autowired
	private AccommodationRepository accommodationRepository;

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
		travelSite.setCategory("Viagens, Turismo, Intercâmbios");
		travelSite.setRating(4.7);
		
		TravelSite travelSite2 = new TravelSite();
		travelSite2.setName("Booking");
		travelSite2.setUrl("booking.com.br");
		travelSite2.setCategory("Viagens, Turismo");
		travelSite2.setRating(4.3);

		Accommodation accommodation = new Accommodation();
		accommodation.setName("Casa na Praia Copacabana");
		accommodation.setPrice(77.50);
		accommodation.setType("Casa");
		accommodation.setAmenities("Wifi, 3 quartos, 1 banheiro, garagem, cozinha, piscina.");
		accommodation.setTravelSite(travelSite);

		Accommodation accommodation2 = new Accommodation();
		accommodation2.setName("Casa na Praia Ubatuba");
		accommodation2.setPrice(55.30);
		accommodation2.setType("Casa");
		accommodation2.setAmenities("Wifi, 2 quartos, 1 banheiro, cozinha, ar-condicionado.");
		accommodation2.setTravelSite(travelSite2);

		userRepository.save(salomao);
		userRepository.save(leonardo);
		travelSiteRepository.save(travelSite);
		travelSiteRepository.save(travelSite2);
		accommodationRepository.save(accommodation);
		accommodationRepository.save(accommodation2);

	}

}
