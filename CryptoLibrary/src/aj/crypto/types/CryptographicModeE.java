package aj.crypto.types;

public enum CryptographicModeE 
{
	
	ENCRYPT_MODE(javax.crypto.Cipher.ENCRYPT_MODE),
	DECRYPT_MODE(javax.crypto.Cipher.DECRYPT_MODE);
	
	public final int value;
	
	private CryptographicModeE(int value)
	{
		this.value = value;
	}

}
