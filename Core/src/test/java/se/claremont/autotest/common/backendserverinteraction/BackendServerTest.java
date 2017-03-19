package se.claremont.autotest.common.backendserverinteraction;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import se.claremont.autotest.common.logging.LogLevel;
import se.claremont.autotest.common.logging.LogPost;
import se.claremont.autotest.common.testcase.TestCase;
import se.claremont.autotest.common.testrun.Settings;
import se.claremont.autotest.common.testrun.TestRun;

/**
 * Created by jordam on 2017-03-19.
 */
public class BackendServerTest {

    @Test
    public void testVersionGetting(){
        TestRun.initializeIfNotInitialized();
        TestRun.setSettingsValue(Settings.SettingParameters.URL_TO_TAF_BACKEND, "http://127.0.0.1:2222/taf");
        BackendServerConnection backendServerConnection = new BackendServerConnection();
        Assume.assumeTrue("No backend server running at '" + TestRun.getSettingsValue(Settings.SettingParameters.URL_TO_TAF_BACKEND), backendServerConnection.isConnected());
        Assert.assertTrue(backendServerConnection.getBackendVersion().contains("TAF"));
    }

    @Test
    public void testPostingTestCase(){
        TestRun.initializeIfNotInitialized();
        TestRun.setSettingsValue(Settings.SettingParameters.URL_TO_TAF_BACKEND, "http://127.0.0.1:2222/taf");
        BackendServerConnection backendServerConnection = new BackendServerConnection();
        Assume.assumeTrue("No backend server running at '" + TestRun.getSettingsValue(Settings.SettingParameters.URL_TO_TAF_BACKEND), backendServerConnection.isConnected());
        Assert.assertTrue(backendServerConnection.getBackendVersion(), backendServerConnection.getBackendVersion().contains("TAF"));
        TestCase testCase = new TestCase();
        testCase.log(LogLevel.INFO, "My log message");
        String response = backendServerConnection.postTestCase(testCase);
        Assert.assertTrue(response, response.toLowerCase().contains("ok"));
        Assert.assertFalse(response, response.toLowerCase().contains("not"));
    }

    @Test
    public void testPostingLogPost(){
        TestRun.initializeIfNotInitialized();
        TestRun.setSettingsValue(Settings.SettingParameters.URL_TO_TAF_BACKEND, "http://127.0.0.1:2222/taf");
        BackendServerConnection backendServerConnection = new BackendServerConnection();
        Assume.assumeTrue("No backend server running at '" + TestRun.getSettingsValue(Settings.SettingParameters.URL_TO_TAF_BACKEND), backendServerConnection.isConnected());
        Assert.assertTrue(backendServerConnection.getBackendVersion(), backendServerConnection.getBackendVersion().contains("TAF"));
        LogPost logPost = new LogPost(LogLevel.INFO, "My log entry.");
        String response = backendServerConnection.postLogPost(logPost);
        Assert.assertTrue(response, response.toLowerCase().contains("ok"));
        Assert.assertFalse(response, response.toLowerCase().contains("not"));
    }
}
