package br.com.uol.model;

public enum PaginasEnum {
	
	PAGINA("Home", "home.xhtml?faces-redirect=true");
	
	private final String key;
    private final String value;

    PaginasEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
