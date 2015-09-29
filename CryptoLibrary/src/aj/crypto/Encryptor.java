package aj.crypto;

import aj.crypto.common.Utils.Transform;
import aj.crypto.model.CryptographicConfiguration;
import aj.crypto.model.Key;
import aj.crypto.types.AlgorithmE;
import aj.crypto.types.BlockCipherModeE;
import aj.crypto.types.CryptographicModeE;
import aj.crypto.types.CryptographicPaddingE;

public class Encryptor extends CryptographicProcessor {

	public Encryptor(AlgorithmE algorithm, BlockCipherModeE cipherMode) {
		super(CryptographicModeE.ENCRYPT_MODE,
				new CryptographicConfiguration(algorithm, cipherMode, CryptographicPaddingE.PKCS5));
	}

	public String getHexEncryptedData(Key key, String data) {
		byte[] transformedData = Transform.FromStringToByteArray(data);
		byte[] encryptionResult = executeCriptographicProcess(key, transformedData);
		return Transform.FromByteArrayToHexString(encryptionResult);
	}

	public String getBase64EncryptedData(Key key, String data) {
		byte[] transformedData = Transform.FromStringToByteArray(data);
		byte[] encryptionResult = executeCriptographicProcess(key, transformedData);
		return Transform.FromByteArrayToBase64String(encryptionResult);
	}
}
