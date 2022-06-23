package it.prova.gestionetratte.service;

import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetratte.dto.TratteDTO;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService {
	
	@Autowired 
	private TrattaRepository trattaRepository;

	@Override
	public List<Tratta> listAllElements(boolean eager) {
		if(eager)
			return trattaRepository.findAllEaager();
		return (List<Tratta>) trattaRepository.findAll();
	}

	@Override
	public Tratta caricaSingoloElemento(Long id) {
		return trattaRepository.findById(id).orElse(null);
	}

	@Override
	public Tratta caricaSingoloElementoEager(Long id) {
		return trattaRepository.findSingleTrattaEager(id);
	}

	@Override
	@Transactional
	public Tratta aggiorna(Tratta trattaInstance) {
		return trattaRepository.save(trattaInstance);
	}

	@Override
	@Transactional
	public Tratta inserisciNuovo(Tratta trattaInstance) {
		return trattaRepository.save(trattaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Tratta trattaInstance) {
		trattaRepository.delete(trattaInstance);
		
	}

	@Override
	public List<Tratta> findByExample(Tratta example) {
		return trattaRepository.findByExample(example);
	}

	@Override
	public List<Tratta> cercaByCodiceEDescrizione(String codice, String descrizione) {
		return trattaRepository.findByCodiceAndDescrizione(codice, descrizione);
	}

	@Override
	@Transactional
	public void concludiTratte() {
		trattaRepository.annullaTratta(LocalTime.now());
		
	}

}
