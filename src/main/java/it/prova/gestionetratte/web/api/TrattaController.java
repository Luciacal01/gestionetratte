package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.TratteDTO;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.TrattaService;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;
import it.prova.gestionetratte.web.api.exception.TrattaNotFoundException;

@RestController
@RequestMapping("api/tratta")
public class TrattaController {
	@Autowired
	private TrattaService trattaService;
	
	@GetMapping
	public List<TratteDTO> getAll() {
		return TratteDTO.createTrattaDTOListFromModelList(trattaService.listAllElements(true), true);
	}
	
	@PostMapping
	public TratteDTO createNew(@Valid @RequestBody TratteDTO trattaInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (trattaInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Tratta trattaInserita = trattaService.inserisciNuovo(trattaInput.buildTrattaModel());
		return TratteDTO.buildTrattaDTOFromModel(trattaInserita, true);
	}
	
	@GetMapping("/{id}")
	public TratteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Tratta tratta = trattaService.caricaSingoloElementoEager(id);

		if (tratta == null)
			throw new TrattaNotFoundException("Tratta not found con id: " + id);

		return TratteDTO.buildTrattaDTOFromModel(tratta, true);
	}
	
	@PutMapping("/{id}")
	public TratteDTO update(@Valid @RequestBody TratteDTO tratteInput, @PathVariable(required = true) Long id) {
		Tratta tratta = trattaService.caricaSingoloElemento(id);

		if (tratta == null)
			throw new TrattaNotFoundException("Tratta not found con id: " + id);

		tratteInput.setId(id);
		Tratta trattaAggiornata = trattaService.aggiorna(tratteInput.buildTrattaModel());
		return TratteDTO.buildTrattaDTOFromModel(trattaAggiornata, false);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Tratta tratta = trattaService.caricaSingoloElemento(id);

		if (tratta == null)
			throw new TrattaNotFoundException("Tratta not found con id: " + id);

		trattaService.rimuovi(tratta);
	}

	@PostMapping("/search")
	public List<TratteDTO> search(@RequestBody TratteDTO example) {
		return TratteDTO.createTrattaDTOListFromModelList(trattaService.findByExample(example.buildTrattaModel()), true);
	}
	
}
