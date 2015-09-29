package aj.crypto.types;

public enum CryptographicPaddingE {
	
	NO_PADDING("NoPadding"),
	PKCS5("PKCS5Padding");
	
	public final String name;
	
	private CryptographicPaddingE(String name) {
		this.name = name;
	}

}
