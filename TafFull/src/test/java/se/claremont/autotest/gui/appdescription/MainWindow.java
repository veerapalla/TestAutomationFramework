package se.claremont.autotest.gui.appdescription;

import se.claremont.autotest.common.guidriverpluginstructure.PositionBasedIdentification.PositionBasedIdentificator;
import se.claremont.autotest.javasupport.interaction.elementidentification.By;
import se.claremont.autotest.javasupport.objectstructure.GuiComponent;
import se.claremont.autotest.javasupport.objectstructure.JavaGuiElement;
import se.claremont.autotest.javasupport.objectstructure.JavaWindow;

public class MainWindow {

    public static JavaWindow mainWindow = new JavaWindow(".*TAF.*Test Automation Framework", "TAF Main window");

    public static JavaGuiElement runTestTab = new JavaGuiElement(mainWindow, By.byTextContaining(". Run tests"), "RunTestTab");

    public static class RunTabPanel {

        public static JavaGuiElement helpButton = new JavaGuiElement(mainWindow,
                By.byExactText("Help"),
                "HelpButton");

        public static JavaGuiElement cliTextArea = new JavaGuiElement(mainWindow,
                By.byName("CliCommandText"),
                "CliTextArea");

        public static JavaGuiElement resetButton = new JavaGuiElement(mainWindow,
                By.byExactText("Reset"),
                "ResetButton");

        public static JavaGuiElement label = new JavaGuiElement(mainWindow,
                By.byExactText("Test run name"),
                "RunNameLabel");

        /*
        public static JavaGuiElement runNameText = (JavaGuiElement)
                PositionBasedIdentificator
                        .fromAllTheElements(mainWindow.getComponentsAsJavaGuiElements())
                        .elementImmediatelyToTheRightOf(label);
        */

        public static JavaGuiElement runNameText = new JavaGuiElement(mainWindow,
                By.byName("RunNameTextField"),
                "RunNameText");

        public static JavaGuiElement executionModeSpinner = new JavaGuiElement(mainWindow,
                By.byName("ExecutionModeSpinner"),
                "ExecutionModeSpinner");

        public static JavaGuiElement setRunParametersButton = new JavaGuiElement(mainWindow,
                By.byExactText("Set run parameters..."),
                "SetRunParametersButton");

        public static JavaGuiElement cliToClipboardButton = new JavaGuiElement(mainWindow,
                By.byExactText("CLI to clipboard"),
                "CliToClipboardButton");

        public static JavaGuiElement startTestRunButton = new JavaGuiElement(mainWindow,
                By.byExactText("Start test run"),
                "StartTestRunButton"); //Should not be used since it produces System exit.

        public static JavaGuiElement exitButton = new JavaGuiElement(mainWindow,
                By.byExactText("Exit"),
                "ExitButton"); //Should not be used since it produces System exit.

    }

    public static class TestRunResultsWindow{

        public static JavaWindow testRunResultsWindow = new JavaWindow("^TAF.*Run result.*", "TAF Test run results window");

        public static JavaGuiElement closeButton = new JavaGuiElement(testRunResultsWindow,
                By.byExactText("Close"),
                "RunResultsWindowCloseButton");

        public static JavaGuiElement summaryReportButton = new JavaGuiElement(testRunResultsWindow,
                By.byExactText("Summary report"),
                "SummaryReportButton");

        public static JavaGuiElement logOutputArea = new JavaGuiElement(testRunResultsWindow,
                By.byClass("TafTextArea"),
                "LogOutputArea");
    }
}
