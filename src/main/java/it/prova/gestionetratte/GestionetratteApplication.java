package it.prova.gestionetratte;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.service.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner{
	
	@Autowired
	private AirbusService airbusService;
	
	@Autowired
	private TrattaService trattaService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String codice="2345";
		String descrizione= "FG50";
		Airbus airbus1= airbusService.cercaByCodiceEDescrizione(codice, descrizione);
		
		if(airbus1== null) {
			airbus1= new Airbus(codice, descrizione, LocalDate.of(2022, 05, 13), 100);
			airbusService.inserisciNuovo(airbus1);
		}
		
		Tratta tratta1= new Tratta("SW9065", "Roma-Firenze", LocalDate.of(2022, 05, 19), LocalTime.of(7, 45), LocalTime.of(9, 30), Stato.ATTIVA);
		tratta1.setAirbus(airbus1);
		
		if(trattaService.cercaByCodiceEDescrizione(tratta1.getCodice(), tratta1.getDescrizione()).isEmpty()) {
			trattaService.inserisciNuovo(tratta1);
		}
		
		String codice2="4560";
		String descrizione2= "GM09";
		Airbus airbus2= airbusService.cercaByCodiceEDescrizione(codice2, descrizione2);
		
		if(airbus2== null) {
			airbus2= new Airbus(codice, descrizione, LocalDate.of(2022, 02, 18), 430);
			airbusService.inserisciNuovo(airbus2);
		}
		
		Tratta tratta2= new Tratta("NB000", "Napoli-Bologna", LocalDate.of(2022, 02, 21), LocalTime.of(12, 20), LocalTime.of(23, 40), Stato.ATTIVA);
		tratta2.setAirbus(airbus2);
		
		if(trattaService.cercaByCodiceEDescrizione(tratta2.getCodice(), tratta2.getDescrizione()).isEmpty()) {
			trattaService.inserisciNuovo(tratta2);
		}
		
	}

}
