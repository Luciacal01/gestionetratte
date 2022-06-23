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
		return airbusRepository.findById(id).orElse(null);
	}

	@Override
	public Airbus caricaSingoloElementoConFilms(Long id) {
		return airbusRepository.findByIdEager(id);
	}

	@Override
	@Transactional
	public Airbus aggiorna(Airbus airbusInstance) {
		return airbusRepository.save(airbusInstance);
	}

	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		return airbusRepository.save(airbusInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Airbus airbusInstance) {
		airbusRepository.delete(airbusInstance);
		
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		return airbusRepository.findByExample(example);
	}

	@Override
	public Airbus cercaByCodiceEDescrizione(String codice, String descrizione) {
		return airbusRepository.findByCodiceAndDescrizione(codice, descrizione);
	}

}
