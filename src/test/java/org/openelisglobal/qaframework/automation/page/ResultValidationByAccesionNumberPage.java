package org.openelisglobal.qaframework.automation.page;

public class ResultValidationByAccesionNumberPage extends Page {

    private static final String PAGE_PATH = "/AccessionValidation.do";

    public ResultValidationByAccesionNumberPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return PAGE_PATH;
    }

}
