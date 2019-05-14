package se.skillytaire.belastingdienst.ee.service;

import java.io.Serializable;
import java.util.Optional;

public abstract class ResultTO<R> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int NO_ERROR = 0;
	public static final int Nieuwe_Klant_Exists = 100;
	public static final int ACCOUNT_NIET_AANGEMAAKT = 200;

	private final int code;
	private final R result;
	
	protected ResultTO(int code) {
		this(code, null);
	}

	protected ResultTO(R result) {
		this(ResultTO.NO_ERROR, result);
	}

	private ResultTO(int code, R result) {
		this.code = code;
		this.result = result;
	}

	public final boolean isSuccessful() {
		return this.code == ResultTO.NO_ERROR;
	}

	/**
	 * @return the code
	 */
	public final int getCode() {
		return code;
	}

	/**
	 * @return the result
	 */
	public final Optional<R> getResult() {
		return Optional.ofNullable(this.result);
	}

	
}