package org.openelisglobal.qaframework.automation.page;

public class ResultValidationPageByAccesionPage extends Page {

    private static final String PAGE_PATH = "/AccessionValidation.do";

    public ResultValidationPageByAccesionPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return PAGE_PATH;
    }

}
