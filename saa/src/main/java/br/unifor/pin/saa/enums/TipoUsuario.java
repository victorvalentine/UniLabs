package br.unifor.pin.saa.enums;

public enum TipoUsuario {
	OPERADOR("OPERADOR"),
	PROFESSOR("PROFESSOR"),
	ALUNO("ALUNO");
	private String value;

	private TipoUsuario(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
