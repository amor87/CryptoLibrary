package aj.crypto.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import aj.crypto.common.Utils.Transform;

public class Key {

	public final String passphrase;
	public final String salt;
	public final Integer iterationCount;
	public final String iv;

	private Key(String passphrase) {
		this(passphrase, "", "");
	}

	private Key(String passphrase, String salt, String iv) {
		// Iteration Count set in 500 is the default value
		this(passphrase, salt, iv, 500);
	}

	private Key(String passphrase, String salt, String iv, Integer iterationCount) {
		this.passphrase = passphrase;
		this.salt = salt;
		this.iv = iv;
		this.iterationCount = iterationCount;
	}

	public static Key GenerateByCustomKey(String passphrase, String salt, String iv) {
		return new Key(passphrase, salt, iv);
	}

	public static Key GenerateByCustomKey(String passphrase, String salt, String iv, Integer iterationCount) {
		return new Key(passphrase, salt, iv, iterationCount);
	}

	public static Key GenerateByGivenFormattedKey(String cryptographicKey) {
		return new Key(cryptographicKey);
	}

	// public static Key GenerateByRandomKey()
	// {
	// return null;
	// }

	public IvParameterSpec getIVAlgorithmParameterSpec() {
		byte[] initializationVectorInByteArray = Transform.FromStringHexToByteArray(iv);
		return new IvParameterSpec(initializationVectorInByteArray);
	}

	public SecretKey getSecretKeySpec(CryptographicConfiguration configuration) {
		if (isCustomKey()) {
			return generateByCustomKeyInfo(configuration);
		} else {
			return generateByFormattedKeyInfo(configuration.getAlgorithm().AlgorithmName);
		}
	}

	private SecretKey generateByCustomKeyInfo(CryptographicConfiguration configuration) {
		SecretKey key = null;
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(/* configuration.getCryptographicPadding().name */"PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(
							passphrase.toCharArray(), 
							Transform.FromStringHexToByteArray(salt),
							iterationCount, 
							configuration.getAlgorithm().KeySize);
			key = new SecretKeySpec(
							factory.generateSecret(spec).getEncoded(),
							configuration.getAlgorithm().AlgorithmName);
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace(); /* algorithm provided and verified */
		} catch (InvalidKeySpecException e) {
			// e.printStackTrace(); /* pkcs provided and verified */
		}
		return key;
	}

	private SecretKey generateByFormattedKeyInfo(String algorithmName) {
		return new SecretKeySpec(passphrase.getBytes(), algorithmName);
	}

	public boolean isCustomKey() {
		return !salt.isEmpty() && !iv.isEmpty();
	}
}