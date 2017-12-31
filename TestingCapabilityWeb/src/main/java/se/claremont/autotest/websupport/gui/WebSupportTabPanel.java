package se.claremont.autotest.websupport.gui;

import se.claremont.autotest.common.gui.Gui;
import se.claremont.autotest.common.gui.createtesttab.TestStepListManager;
import se.claremont.autotest.common.gui.guistyle.*;
import se.claremont.autotest.common.gui.plugins.IGuiTab;
import se.claremont.autotest.common.gui.teststructure.TestStep;
import se.claremont.autotest.common.gui.teststructure.TestStepList;
import se.claremont.autotest.common.testcase.TestCase;
import se.claremont.autotest.websupport.gui.recorder.RecorderWindow;
import se.claremont.autotest.websupport.webdrivergluecode.WebInteractionMethods;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WebSupportTabPanel implements IGuiTab{
    TafPanel panel = null;
    TafHeadline headline = new TafHeadline("Web support");
    TafLabel text = new TafLabel("Web is supported through code, for now.");
    TafButton recordButton = new TafButton("Record");
    TafButton startTestButton = new TafButton("Start test");

    public WebSupportTabPanel(){
        panel = new TafPanel("WebSupportTabPanel");

        recordButton.setMnemonic('r');
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecorderWindow recorderWindow = new RecorderWindow();
            }
        });
        TafFrame application;
        if(StandaloneWebSupportGui.frame == null){
            application = Gui.applicationWindow;
        } else{
            application = StandaloneWebSupportGui.frame;
        }

        TestStepListManager testStepListManager = new TestStepListManager("Web", application);
        Gui.availableTestSteps.addChangeListener(new TestStepList.TestStepListChangeListener() {
            @Override
            public void isAdded(TestStep testStep) {
                testStepListManager.update();
            }

            @Override
            public void isRemoved(TestStep testStep) {
                testStepListManager.update();
            }
        });

        startTestButton.setMnemonic('s');
        startTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestCase testCase = new TestCase();
                List<Object> clickedElements = new ArrayList<>();
                try{
                    WebInteractionMethods web = new WebInteractionMethods(testCase);
                    web.navigate("http://www.google.com");

                    Object o = web.executeJavascript(
                            "document.addEventListener(\"click\", function(event) { return event.target; }" +
                            ", false);");
                        System.out.println("Clicked " + o);
                        clickedElements.add(o);
                }catch (Throwable t){
                    System.out.println(t);
                } finally {
                    //
                }
            }
        });

        GroupLayout groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(headline)
                                .addComponent(text)
                                .addComponent(testStepListManager)
                                .addComponent(recordButton)
                                .addComponent(startTestButton)
                        )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(headline)
                        .addComponent(text)
                        .addComponent(testStepListManager)
                        .addComponent(recordButton)
                        .addComponent(startTestButton)
        );

    }

    @Override
    public JPanel getPanel() {
        if(panel == null) return new WebSupportTabPanel().panel;
        return panel;
    }

    @Override
    public String getName() {
        return "Web";
    }
}
