package it.prova.gestionetratte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
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
		
		
	}

}
