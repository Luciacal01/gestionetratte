package it.prova.gestionetratte.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService {
	
	@Autowired
	private AirbusRepository airbusRepository;

	@Override
	public List<Airbus> listAllElements() {
		return (List<Airbus>) airbusRepository.findAll();
	}

	@Override
	public List<Airbus> listAllElementsEager() {
		return (List<Airbus>) airbusRepository.findAllEager();
	}

	@Override
	public Airbus caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus caricaSingoloElementoConFilms(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus aggiorna(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		return airbusRepository.save(airbusInstance);
	}

	@Override
	public void rimuovi(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus cercaByCodiceEDescrizione(String codice, String descrizione) {
		return airbusRepository.findByCodiceAndDescrizione(codice, descrizione);
	}

}
