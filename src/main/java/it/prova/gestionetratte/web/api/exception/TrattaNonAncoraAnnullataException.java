package it.prova.gestionetratte.web.api.exception;

public class TrattaNonAncoraAnnullataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TrattaNonAncoraAnnullataException(String message) {
		super(message);
	}
}
