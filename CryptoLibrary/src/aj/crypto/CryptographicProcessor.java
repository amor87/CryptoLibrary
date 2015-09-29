package aj.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import aj.crypto.model.CryptographicConfiguration;
import aj.crypto.types.CryptographicModeE;

abstract class CryptographicProcessor {

	private CryptographicModeE cryptographicMode;
	private CryptographicConfiguration configuration;
	private Cipher cipher;

	public CryptographicProcessor(CryptographicModeE cryptographicMode, CryptographicConfiguration configuration) {
		this.cryptographicMode = cryptographicMode;
		this.configuration = configuration;
		try {
			cipher = Cipher.getInstance(this.configuration.getCryptographicInstance());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	protected byte[] executeCriptographicProcess(aj.crypto.model.Key key, byte[] data) {
		SecretKey secretKeySpec = key.getSecretKeySpec(configuration);
		if (key.isCustomKey()) {
			executeCipherInitializationForCustomKey(secretKeySpec, key.getIVAlgorithmParameterSpec());
		} else {
			executeCipherInitializationForFormattedKey(secretKeySpec);
		}
		return getCipherOperationFinalizationResult(data);
	}

	private void executeCipherInitializationForCustomKey(SecretKey key, IvParameterSpec iv) {
		try {
			cipher.init(cryptographicMode.value, key, iv);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

	private void executeCipherInitializationForFormattedKey(SecretKey key) {
		try {
			cipher.init(cryptographicMode.value, key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	private byte[] getCipherOperationFinalizationResult(byte[] data) {
		byte[] cryptographicProcessedData;
		try {
			cryptographicProcessedData = cipher.doFinal(data);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			cryptographicProcessedData = new byte[] { 0x00 };
		}
		return cryptographicProcessedData;
	}

}
