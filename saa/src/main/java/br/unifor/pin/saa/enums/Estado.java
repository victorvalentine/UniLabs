package br.unifor.pin.saa.enums;

public enum Estado {
	LIVRE("LIVRE"),
	PENDENTE("PENDENTE"),
	OCUPADO("OCUPADO");
	
	private String value;

	private Estado(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
