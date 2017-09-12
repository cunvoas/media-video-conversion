package com.github.cunvoas.mediavideoconversion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.cunvoas.mediavideoconversion.convert.TestConvert;
import com.github.cunvoas.mediavideoconversion.tasks.TestConversionTask;
import com.github.cunvoas.mediavideoconversion.vlc.TestVlcCommandLine;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			TestVlcCommandLine.class, 
			TestConversionTask.class, 
			TestConvert.class
		} )
public class AllTests {

}
