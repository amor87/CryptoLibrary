package aj.crypto;

import aj.crypto.model.Key;
import aj.crypto.types.AlgorithmE;
import aj.crypto.types.BlockCipherModeE;

public class Main 
{

	public static void main(String[] args) 
	{
		//creas la llave
		String formattedAESKey = "*user_pass_kek_*";
		Key key = Key.GenerateByGivenFormattedKey(formattedAESKey);
		
		//declaras las clases para encriptar y desencriptar con su configuracion
		Encryptor encryptor = new Encryptor(AlgorithmE.AES_192, BlockCipherModeE.ECB);
		Decryptor decryptor = new Decryptor(AlgorithmE.AES_192, BlockCipherModeE.ECB);
		
		String originalData = "test";
		
		//encriptas
		String encryptedData = encryptor.getHexEncryptedData(key, originalData);
		
		System.out.println("Original Data: "+ originalData);
		System.out.println("Encrypted Data: "+ encryptedData);
		//desencriptas
		System.out.println("Decrypted Data: "+ decryptor.getDataFromHexEncryptedString(key, encryptedData));
		
	}

}
