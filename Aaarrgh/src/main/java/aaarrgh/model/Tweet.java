package aaarrgh.model;

public class Tweet {
	private Integer id;
	private String texto;
	private Integer idPersona;

	
	public Tweet() {
		super();
	}


	public Integer getIdTweet() {
		return id;
	}


	public void setIdTweet(Integer idTweet) {
		this.id = idTweet;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	
}
