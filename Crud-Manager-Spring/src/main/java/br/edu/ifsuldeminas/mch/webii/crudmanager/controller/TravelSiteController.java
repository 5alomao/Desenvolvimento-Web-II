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

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.TravelSite;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AccommodationRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.TravelSiteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/travel-sites")
public class TravelSiteController {

	@Autowired
	private TravelSiteRepository travelSiteRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;

	@GetMapping
	public String listTravelSites(Model model) {

		List<TravelSite> travelSites = travelSiteRepository.findAll();

		model.addAttribute("travelSites", travelSites);

		return "travel_sites";
	}

	@GetMapping("/form")
	public String travelSiteForm(@ModelAttribute("travelSite") TravelSite travelSite) {
		// ESTUDAR BINDING DO SPRING

		return "travel_sites_form";
	}

	@PostMapping("/register")
	public String travelSiteNew(@Valid @ModelAttribute("travelSite") TravelSite travelSite, BindingResult err) {

		if (err.hasErrors()) {
			return "travel_sites_form";
		}

		travelSiteRepository.save(travelSite);

		return "redirect:/travel-sites";
	}

	@GetMapping("/update/{id}")
	public String travelSiteUpdate(@PathVariable("id") Integer id, Model model) {

		Optional<TravelSite> travelSiteOptional = travelSiteRepository.findById(id);
		TravelSite travelSite;

		if (!travelSiteOptional.isPresent()) {
			travelSite = new TravelSite();
		} else {
			travelSite = travelSiteOptional.get();
		}

		model.addAttribute("travelSite", travelSite);

		return "travel_sites_form";
	}

	@GetMapping("/delete/{id}")
	public String deleteTravelSite(@PathVariable("id") Integer id, Model model) {

		if (accommodationRepository.existsByTravelSiteId(id)) {
			Optional<TravelSite> travelSite = travelSiteRepository.findById(id);
			model.addAttribute("errorMessage", "O Site de Viagens: " + travelSite.get().getName()
					+ " não pode ser removido pois está em uso em uma ou mais hospedagens.");
			return listTravelSites(model);
		}

		travelSiteRepository.delete(new TravelSite(id));

		return "redirect:/travel-sites";
	}
}
