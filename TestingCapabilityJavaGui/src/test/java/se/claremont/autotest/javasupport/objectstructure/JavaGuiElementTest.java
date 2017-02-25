package se.claremont.autotest.javasupport.objectstructure;

import org.junit.*;
import se.claremont.autotest.javasupport.interaction.JavaTestApplication;
import se.claremont.autotest.javasupport.interaction.JavaTestApplicationRunner;

/**
 * Created by jordam on 2017-02-25.
 */
public class JavaGuiElementTest {

    @Before
    public void setup(){
        JavaTestApplicationRunner.tryStart();
    }

    @Test
    public void getParent(){
        Assert.assertTrue(JavaTestApplication.okbutton().getParent().getName(), JavaTestApplication.okbutton().getParent().getName().toLowerCase().contains("panel"));
    }

    @Test
    public void getSubElements(){
        System.out.println(JavaTestApplication.panel0().getSubElements().size());
        Assert.assertTrue(JavaTestApplication.panel0().getSubElements().size() == 6);
    }

    @Test
    public void getElementsInTheSameComponent(){
        System.out.println(JavaTestApplication.okbutton().getAllElementsInSameContainer().size());
        Assert.assertTrue(JavaTestApplication.okbutton().getAllElementsInSameContainer().size() == 6);
    }
}
