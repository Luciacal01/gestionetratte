package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.TratteDTO;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.TrattaService;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;

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
	
}
