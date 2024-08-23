package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
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
	@Digits(integer = 10, fraction = 2, message = "Preço inválido.")
	private BigDecimal price;

	@NotBlank(message = "As comodidades são obrigatórias.")
	private String amenities;

	@OneToOne(optional = false)
	@Valid
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

}
