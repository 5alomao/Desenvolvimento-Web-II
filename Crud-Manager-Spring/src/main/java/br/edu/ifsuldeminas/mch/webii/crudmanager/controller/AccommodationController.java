package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Accommodation;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.TravelSite;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AccommodationRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.TravelSiteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/accommodations")
public class AccommodationController {

	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	private TravelSiteRepository travelSiteRepository;

	@GetMapping
	public String listAccommodations(Model model) {

		List<Accommodation> accommodations = accommodationRepository.findAll();

		model.addAttribute("accommodations", accommodations);

		return "accommodation_page";
	}

	@GetMapping("/form")
	public String accommodationForm(@ModelAttribute("accommodation") Accommodation accommodation, Model model) {
		// ESTUDAR BINDING DO SPRING

		List<TravelSite> travelSites = travelSiteRepository.findAll();
		model.addAttribute("travelSites", travelSites);

		return "accommodation_form";
	}

	@PostMapping("/register")
	public String accommodationNew(@Valid @ModelAttribute("accommodation") Accommodation accommodation,
			BindingResult err, Model model) {

		if (err.hasErrors()) {
			List<TravelSite> travelSites = travelSiteRepository.findAll();
			model.addAttribute("travelSites", travelSites);
			return "accommodation_form";
		}

		accommodationRepository.save(accommodation);

		return "redirect:/accommodations";
	}

	@GetMapping("/update/{id}")
	public String accommodationUpdate(@PathVariable("id") Integer id, Model model) {

		Optional<Accommodation> accommodationOptional = accommodationRepository.findById(id);
		Accommodation accommodation;

		if (!accommodationOptional.isPresent()) {
			accommodation = new Accommodation();
		} else {
			accommodation = accommodationOptional.get();
		}

		List<TravelSite> travelSites = travelSiteRepository.findAll();
		model.addAttribute("accommodation", accommodation);
		model.addAttribute("travelSites", travelSites);

		return "accommodation_form";
	}

	@GetMapping("/delete/{id}")
	public String deleteAccommodation(@PathVariable("id") Integer id) {

		accommodationRepository.delete(new Accommodation(id));

		return "redirect:/accommodations";
	}

}
