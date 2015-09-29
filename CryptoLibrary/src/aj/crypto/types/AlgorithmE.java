package aj.crypto.types;

public enum AlgorithmE {
	AES_128("AES", 128), 
	AES_192("AES", 192), 
	DES("DES", 48), 
	TRIPLE_DES("DESede", 64);

	public final String AlgorithmName;
	public final int KeySize;

	private AlgorithmE(String algorithmName, int keySize) {
		this.AlgorithmName = algorithmName;
		this.KeySize = keySize;
	}
}
