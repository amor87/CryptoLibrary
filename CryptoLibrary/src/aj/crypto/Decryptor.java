package aj.crypto;

import aj.crypto.common.Utils.Transform;
import aj.crypto.model.CryptographicConfiguration;
import aj.crypto.model.Key;
import aj.crypto.types.AlgorithmE;
import aj.crypto.types.BlockCipherModeE;
import aj.crypto.types.CryptographicModeE;
import aj.crypto.types.CryptographicPaddingE;

public class Decryptor extends CryptographicProcessor
{

	public Decryptor(AlgorithmE algorithm, BlockCipherModeE cipherMode) 
	{
		super(CryptographicModeE.DECRYPT_MODE, new CryptographicConfiguration(algorithm, cipherMode, CryptographicPaddingE.NO_PADDING));
	}
	
	public String getDataFromHexEncryptedString(Key key, String data)
	{
		byte [] transformedData = Transform.FromHexStringToByteArray(data);
		byte [] encryptionResult = executeCriptographicProcess(key, transformedData);
		return Transform.FromByteArrayToString(encryptionResult);
	}
	
	public String getDataFromBase64EncryptedString(Key key, String data)
	{
		byte [] transformedData = Transform.FromStringBase64toByteArray(data);
		byte [] encryptionResult = executeCriptographicProcess(key, transformedData);
		return Transform.FromByteArrayToString(encryptionResult);
	}
}
