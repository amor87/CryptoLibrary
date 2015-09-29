package test.crypto.algorithm;

import aj.crypto.Decryptor;
import aj.crypto.Encryptor;
import aj.crypto.model.Key;
import aj.crypto.types.AlgorithmE;
import aj.crypto.types.BlockCipherModeE;

public class CipherTestAES128 {

	public Encryptor encryptor;
	public Decryptor decryptor;

	public String onClearText = "long live the king";

	@org.junit.Before
	public void setUp() {
		encryptor = new Encryptor(AlgorithmE.AES_128, BlockCipherModeE.ECB);
		decryptor = new Decryptor(AlgorithmE.AES_128, BlockCipherModeE.ECB);
	}

	@org.junit.Test
	public void testEncryptorInitialized() {
		org.junit.Assert.assertNotNull(encryptor);
	}

	@org.junit.Test
	public void testDecryptorInitialized() {
		org.junit.Assert.assertNotNull(decryptor);
	}

	@org.junit.Test
	public void testHexTextCiphering() {
		Key cryptographicKey = createKey();
		String hexEncryptedData = encryptor.getHexEncryptedData(cryptographicKey, onClearText);
		org.junit.Assert.assertEquals(onClearText,
				decryptor.getDataFromHexEncryptedString(cryptographicKey, hexEncryptedData));
	}

	@org.junit.Test
	public void testBase64TextCiphering() {
		Key cryptographicKey = createKey();
		String base64EncryptedData = encryptor.getBase64EncryptedData(cryptographicKey, onClearText);
		org.junit.Assert.assertEquals(onClearText,
				decryptor.getDataFromBase64EncryptedString(cryptographicKey, base64EncryptedData));
	}

	private aj.crypto.model.Key createKey() {
		String formattedAES128Key = "*7eSt-Key*54Mp13";
		org.junit.Assert.assertEquals(16, formattedAES128Key.length());
		return Key.GenerateByGivenFormattedKey(formattedAES128Key);
	}

	// External Online Tool https://www.tools4noobs.com/online_tools/encrypt/
	@org.junit.Test
	public void testDecryptHexDataFromExternalTool() {
		String key = "&*3x7ErN4L#k3y$=";
		String encryptedText = "2613b34911b5d05b805099db3cfc4c728e33f90fa0c0252811e44f11813658cb9378dfbb0195f72ce78929fc79c77df5d81128e56742a189dcf3b58bbb5a8c84";

		Key cryptographicKey = Key.GenerateByGivenFormattedKey(key);
		String decryptedText = decryptor.getDataFromHexEncryptedString(cryptographicKey, encryptedText);
		org.junit.Assert.assertEquals("this is a text encrypted from tools4noob.com web page", decryptedText);
	}

	@org.junit.Test
	public void testDecryptBase64DataFromExternalTool() {
		String key = "&*3x7ErN4L#k3y$=";
		String encryptedText = "3su1dZkVYbZ/yb9p1RMxlJJ+31Wile0bAJ6xikff9b49L0Aw3p4Djxvd++OAjGbVPISARSEpy9Dt43p8ccpCsgaBonvLsaSrlry2DjG0q7A=";

		Key cryptographicKey = Key.GenerateByGivenFormattedKey(key);
		String decryptedText = decryptor.getDataFromBase64EncryptedString(cryptographicKey, encryptedText);
		org.junit.Assert.assertEquals("another text encrypted from tools4noob.com web page with base64 outpur",
				decryptedText);
	}

}
