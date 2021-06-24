package org.openelisglobal.qaframework.automation.page;

public class ResultsEntryPage extends Page {

	private static final String PATH_RESULT_ENTRY = "/LogbookResults.do";

	public ResultsEntryPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return PATH_RESULT_ENTRY;
	}

}
