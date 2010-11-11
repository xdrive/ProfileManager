package test.com.xdrive.profilemanager;

import test.com.xdrive.profilemanager.rule.TimeRuleTest;
import junit.framework.TestSuite;
import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

public class ProfileManagerInstrumentationTestRunner 
		extends InstrumentationTestRunner{
	@Override
	public TestSuite getAllTests() {
		InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
		suite.addTestSuite(TimeRuleTest.class);
		return suite;
	}
	
	@Override
	public ClassLoader getLoader() {
		return ProfileManagerInstrumentationTestRunner.class.getClassLoader();
	}
}
