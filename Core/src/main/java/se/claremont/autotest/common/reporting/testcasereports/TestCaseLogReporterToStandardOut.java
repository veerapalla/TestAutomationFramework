package se.claremont.autotest.common.reporting.testcasereports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.claremont.autotest.common.logging.LogPost;
import se.claremont.autotest.common.testcase.TestCase;

/**
 * Non-file based log reporter for test case logs. Writes the log to standard out.
 *
 * Created by jordam on 2016-09-18.
 */
class TestCaseLogReporterToStandardOut {
    private final static Logger logger = LoggerFactory.getLogger( TestCaseLogReporterToStandardOut.class );
    private final TestCase testCase;

    @SuppressWarnings("unused")
    TestCaseLogReporterToStandardOut(TestCase testCase){
        this.testCase = testCase;
    }

    @SuppressWarnings("unused")
    public void report(){
        logger.debug( "Log for test case '" + testCase.testName + "' in test set '" + testCase.testSetName + "':" );
        for(LogPost logPost : testCase.testCaseResult.testCaseLog.logPosts){
            System.out.println(logPost.toString());
        }
    }
}
