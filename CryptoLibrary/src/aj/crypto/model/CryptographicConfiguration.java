package aj.crypto.model;

import aj.crypto.types.AlgorithmE;
import aj.crypto.types.BlockCipherModeE;
import aj.crypto.types.CryptographicPaddingE;

public class CryptographicConfiguration {

	private AlgorithmE algorithm;
	private BlockCipherModeE blockCipherMode;
	private CryptographicPaddingE cryptographicPadding;

	public CryptographicConfiguration(AlgorithmE algorithm, 
										BlockCipherModeE blockCipherMode,
											CryptographicPaddingE cryptographicPadding) {
		this.algorithm = algorithm;
		this.blockCipherMode = blockCipherMode;
		this.cryptographicPadding = cryptographicPadding;
	}

	public AlgorithmE getAlgorithm() {
		return algorithm;
	}

	public BlockCipherModeE getBlockCipherMode() {
		return blockCipherMode;
	}

	public CryptographicPaddingE getCryptographicPadding() {
		return cryptographicPadding;
	}

	public String getCryptographicInstance() {
		return this.algorithm.AlgorithmName + "/" + this.blockCipherMode.name + "/" + this.cryptographicPadding.name;
	}
}