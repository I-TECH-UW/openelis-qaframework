package org.openelisglobal.qaframework.automation.page;

public class ElectronicOrderPage extends Page{

    public ElectronicOrderPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return "/ElectronicOrders.do";
    }
    
}
