package test.crypto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.crypto.algorithm.CipherTestAES128;
import test.crypto.algorithm.CipherTestDES;
import test.crypto.algorithm.CipherTestTripleDES;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	CipherTestAES128.class, 
	CipherTestDES.class, 
	CipherTestTripleDES.class 
	})
public class CipherTestSuite {

}
