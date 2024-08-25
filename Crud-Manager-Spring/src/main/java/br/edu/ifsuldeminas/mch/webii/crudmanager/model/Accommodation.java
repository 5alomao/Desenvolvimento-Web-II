package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Accommodation {

	public Accommodation() {
	}

	public Accommodation(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "O nome da hospedagem é obrigatório.")
	private String name;

	@NotBlank(message = "O tipo da hospedagem é obrigatório.")
	private String type;

	@NotNull(message = "O preço da hospedagem é obrigatório.")
	@DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior do que R$0,00")
	private double price;

	@NotBlank(message = "As comodidades são obrigatórias.")
	private String amenities;

	@ManyToOne(optional = false)
	@JoinColumn(name = "travel_site_id")
	@Valid
	@NotNull(message = "O site de viagem é obrigatório.")
	private TravelSite travelSite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TravelSite getTravelSite() {
		return travelSite;
	}

	public void setTravelSite(TravelSite travelSite) {
		this.travelSite = travelSite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

}
