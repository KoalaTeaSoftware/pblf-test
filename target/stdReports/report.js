$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/testSuite/features/smoke.feature");
formatter.feature({
  "name": "SUT Page titles and delivery",
  "description": "  If these pages do not draw fully, then further testing is very likely to give false failures.",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@smoke"
    }
  ]
});
formatter.scenario({
  "name": "Visit a page",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@smoke"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I navigate to the page \"\"",
  "keyword": "When "
});
formatter.match({
  "location": "testFramework.steps.HtmlPageSteps.iNavigateToThePage(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the page is fully drawn",
  "keyword": "Then "
});
formatter.match({
  "location": "testSuite.steps.CommonSteps.thePageIsFullyDrawn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the page title is \"PBLF: Home\"",
  "keyword": "And "
});
formatter.match({
  "location": "testFramework.steps.HtmlPageSteps.thePageTitleIs(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});