package aj.crypto.types;

public enum BlockCipherModeE 
{
	
	ECB("ECB"),
	CBC("CBC"),
	/*CFB("CFB"),
	OFB("OFB"),
	NOFB("NOFB")*/;
	
	public final String name;
	
	private BlockCipherModeE(String name)
	{
		this.name = name;
	}

}
