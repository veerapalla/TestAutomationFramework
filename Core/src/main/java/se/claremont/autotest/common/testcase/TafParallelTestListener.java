package se.claremont.autotest.common.testcase;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

class TafParallelTestListener extends RunListener {

    @Override
    public void testStarted(Description description){
        System.out.println("Starting: " +description.getClassName() + "." + description.getMethodName());
    }
}
