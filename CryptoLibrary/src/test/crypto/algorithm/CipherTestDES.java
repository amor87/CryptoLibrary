package test.crypto.algorithm;

import aj.crypto.Decryptor;
import aj.crypto.Encryptor;
import aj.crypto.model.Key;
import aj.crypto.types.AlgorithmE;
import aj.crypto.types.BlockCipherModeE;

public class CipherTestDES 
{
	
	public Encryptor encryptor;
	public Decryptor decryptor;
	
	public String onClearText = "long live the king";
	
	@org.junit.Before
	public void setUp()
	{
		encryptor = new Encryptor(AlgorithmE.DES, BlockCipherModeE.ECB);
		decryptor = new Decryptor(AlgorithmE.DES, BlockCipherModeE.ECB);
	}
	
	@org.junit.Test
	public void testEncryptorInitialized()
	{	
		org.junit.Assert.assertNotNull(encryptor);
	}
	
	@org.junit.Test
	public void testDecryptorInitialized()
	{
		org.junit.Assert.assertNotNull(decryptor);
	}
	
	@org.junit.Test
	public void testHexTextCiphering()
	{
		Key cryptographicKey = createKey();
		String hexEncryptedData = encryptor.getHexEncryptedData(cryptographicKey, onClearText);
		org.junit.Assert.assertEquals(onClearText, decryptor.getDataFromHexEncryptedString(cryptographicKey, hexEncryptedData));
	}
	
	@org.junit.Test
	public void testBase64TextCiphering()
	{
		Key cryptographicKey = createKey();
		String base64EncryptedData = encryptor.getBase64EncryptedData(cryptographicKey, onClearText);
		org.junit.Assert.assertEquals(onClearText, decryptor.getDataFromBase64EncryptedString(cryptographicKey, base64EncryptedData));
	}
	
	private aj.crypto.model.Key createKey()
	{
		String formattedDES128Key = "7eSt-Key";
		org.junit.Assert.assertEquals(8, formattedDES128Key.length());
		return Key.GenerateByGivenFormattedKey(formattedDES128Key);
	}
	
	//External Online Tool https://www.tools4noobs.com/online_tools/encrypt/
	@org.junit.Test
	public void testDecryptHexDataFromExternalTool()
	{
		String key = "x7rNLk3y";
		String encryptedText = "4116d8af517fe54fd351bda094f0f76f03d8e28ad1b2940d7db7f3fd6a3956a880ce95fe9123a6c36965fd97914b11ccd548b162973f278d";
		
		Key cryptographicKey = Key.GenerateByGivenFormattedKey(key);
		String decryptedText = decryptor.getDataFromHexEncryptedString(cryptographicKey , encryptedText);
		org.junit.Assert.assertEquals("this is a text encrypted from tools4noob.com web page", decryptedText);
	}
	
	@org.junit.Test
	public void testDecryptBase64DataFromExternalTool()
	{
		String key = "x7rNLk3y";
		String encryptedText = "AENDB8GTRCDTPXim7Q/o7ZaimP0g26jGBsmngmU+rbbRSYjMkbETb4OBzvs65+lB3TniWCOEVQvgwPK40SpLAWPb243qAQEv";
		
		Key cryptographicKey = Key.GenerateByGivenFormattedKey(key);
		String decryptedText = decryptor.getDataFromBase64EncryptedString(cryptographicKey , encryptedText);
		org.junit.Assert.assertEquals("another text encrypted from tools4noob.com web page with base64 output", decryptedText);
	}

}
