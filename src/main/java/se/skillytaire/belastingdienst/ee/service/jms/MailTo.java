package se.skillytaire.belastingdienst.ee.service.jms;

public class MailTo {
	private final String to;
	private final String from;
	private final String message;
	
	public MailTo(String to, String from, String message) {
		super();
		this.to = to;
		this.from = from;
		this.message = message;
	}
	public String getTo() {
		return to;
	}
	public String getFrom() {
		return from;
	}
	public String getMessage() {
		return message;
	}
	
}
