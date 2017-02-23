package se.claremont.autotest.common.testrun;

import org.junit.*;

import java.io.*;

/**
 * Created by jordam on 2017-02-22.
 */
public class CliTestRunnerTest {

    String cliIsInvokedWelcomeString = "Executing TAF (TestAutomationFramework) from CLI";

    static PrintStream originalOutputChannel;
    static ByteArrayOutputStream testOutputChannel;

    private void restoreOutputChannel(){
        System.setOut(originalOutputChannel);
    }

    @BeforeClass
    public static void rememberOriginalOutputChannel(){
        originalOutputChannel = System.out;
    }

    @Before
    public void changeOutPutChannel(){
        testOutputChannel = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutputChannel));
    }

    @After
    public void restoreOutPutChannelAfterTest(){
        System.setOut(originalOutputChannel);
    }

    @AfterClass
    public static void restore(){
        System.setOut(originalOutputChannel);
    }

    @Test
    public void usageInstructionsShouldBeWrittenOnNoArguments(){
        String[] args = {};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Usage instruction"));
    }

    @Test
    public void usageInstructionsShouldBeWrittenOnHelpArgument(){
        String[] args = {"help"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Usage instruction"));
    }

    @Test
    public void usageInstructionsShouldBeWrittenOnHelpArgumentRegardlessOfCapitalLetters(){
        String[] args = {"hELp"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Usage instruction"));
    }

    @Test
    public void usageInstructionsShouldBeWrittenOnManArgumentRegardlessOfCapitalLetters(){
        String[] args = {"mAn"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Usage instruction"));
    }

    @Test
    public void usageInstructionsShouldBeWrittenOnManArgument(){
        String[] args = {"man"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Usage instruction"));
    }

    @Test
    public void usageInstructionsShouldBeWrittenOnHArgument(){
        String[] args = {"-h"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Usage instruction"));
    }

    @Test
    public void diagnosticsKeyWordShouldRunDiagnosticsTests(){
        String[] args = {"diagnostics"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Running diagnostic"));
    }

    @Test
    public void runSettingsFileShouldBeSetIfStatedInCLI(){
        String[] args = {"settingsfile=/Temp/MySettings.settings"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Run settings properties file = '/Temp/MySettings.settings'"));
    }

    @Test
    public void settingsShouldBeStatedFromCli(){
        String[] args = {"PATH_TO_LOGO=http://mysite.com/logo.png"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("http://mysite.com/logo.png"));
        Assert.assertTrue(testOutputChannel.toString(), TestRun.getSettingsValue(Settings.SettingParameters.PATH_TO_LOGO).equals("http://mysite.com/logo.png"));
    }

    @Test
    public void settingsShouldBeStatedFromCliAndNotBeCaseSensitive(){
        String[] args = {"pATH_tO_LOgO=http://mysite.com/logo.png"};
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("http://mysite.com/logo.png"));
        Assert.assertTrue(testOutputChannel.toString(), TestRun.getSettingsValue(Settings.SettingParameters.PATH_TO_LOGO).equals("http://mysite.com/logo.png"));
    }

    @Test
    public void settingsShouldBeOverruledFromCli(){
        String[] args = {"PATH_TO_LOGO=http://mysite.com/logo.png"};
        TestRun.initializeIfNotInitialized();
        TestRun.setSettingsValue(Settings.SettingParameters.PATH_TO_LOGO, "dummylogo.jpg");
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("http://mysite.com/logo.png"));
        Assert.assertTrue(testOutputChannel.toString(), TestRun.getSettingsValue(Settings.SettingParameters.PATH_TO_LOGO).equals("http://mysite.com/logo.png"));
    }

    @Test
    public void customSettingsShouldBeSetFromCli(){
        String[] args = {"MyCustomSetting=Happy"};
        TestRun.initializeIfNotInitialized();
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Happy"));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("MyCustomSetting"));
        Assert.assertTrue(testOutputChannel.toString(), TestRun.getCustomSettingsValue("MyCustomSetting").equals("Happy"));
    }

    @Test
    public void testRunNameShouldBeSetFromCli(){
        String[] args = {"runname=HappyTest"};
        TestRun.initializeIfNotInitialized();
        CliTestRunner.runInTestMode(args);
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains(cliIsInvokedWelcomeString));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("runname=HappyTest"));
        Assert.assertTrue(testOutputChannel.toString(), testOutputChannel.toString().contains("Run name is set to 'HappyTest'"));
        Assert.assertTrue(testOutputChannel.toString(), TestRun.testRunName.equals("HappyTest"));
    }
}