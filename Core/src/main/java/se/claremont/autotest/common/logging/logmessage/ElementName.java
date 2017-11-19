package se.claremont.autotest.common.logging.logmessage;

/**
 * Created by jordam on 2017-03-29.
 */
public class ElementName implements LogMessagePartType{
    private String logMessage;

    public ElementName(String elementName){
        this.logMessage = elementName;
    }

    public String toString(){
        return "'" + logMessage + "'";
    }

    public String toHtml(){
        return "<span class=\"elementname\">" + logMessage + "</span>";
    }

    public String toKnownErrorSuggestionString(){
        return toString();
    }

}
