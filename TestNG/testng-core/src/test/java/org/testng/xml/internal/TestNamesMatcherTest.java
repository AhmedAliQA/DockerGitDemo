package org.testng.xml.internal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.TestNGException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import test.SimpleBaseTest;

public class TestNamesMatcherTest extends SimpleBaseTest {

  @Test
  public void testCloneIfContainsTestsWithNamesMatchingAny() {
    XmlSuite suite = createDummySuiteWithTestNamesAs("test1", "test2");
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(suite, Collections.singletonList("test2"));
    List<XmlTest> xmlTests = testNamesMatcher.getMatchedTests();
    assertThat(suite.getTests()).hasSameElementsAs(xmlTests);
  }

  @Test(description = "GITHUB-1594", dataProvider = "getTestnames")
  public void testCloneIfContainsTestsWithNamesMatchingAnyChildSuites(
      String testname, boolean foundInParent, boolean foundInChildOfChild) {
    XmlSuite parentSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    parentSuite.setName("parent_suite");
    XmlSuite childSuite = createDummySuiteWithTestNamesAs("test3", "test4");
    childSuite.setName("child_suite");
    parentSuite.getChildSuites().add(childSuite);
    XmlSuite childOfChildSuite = createDummySuiteWithTestNamesAs("test5", "test6");
    childSuite.getChildSuites().add(childOfChildSuite);
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(parentSuite, Collections.singletonList(testname));
    List<XmlTest> xmlTests = testNamesMatcher.getMatchedTests();
    if (foundInParent) {
      assertThat(xmlTests).hasSameElementsAs(parentSuite.getTests());
    } else if (!foundInChildOfChild) {
      assertThat(xmlTests).hasSameElementsAs(childSuite.getTests());
    } else {
      assertThat(xmlTests).hasSameElementsAs(childOfChildSuite.getTests());
    }
  }

  @Test(
      expectedExceptions = TestNGException.class,
      expectedExceptionsMessageRegExp = "\nPlease provide a valid list of names to check.",
      dataProvider = "getData")
  public void testCloneIfContainsTestsWithNamesMatchingAnyNegativeCondition(
      XmlSuite xmlSuite, List<String> names) {
    TestNamesMatcher testNamesHelper = new TestNamesMatcher(xmlSuite, names);
  }

  @Test(
      expectedExceptions = TestNGException.class,
      expectedExceptionsMessageRegExp = "\nPlease provide a valid list of names to check.",
      dataProvider = "getData")
  public void
      testCloneIfContainsTestsWithNamesMatchingAnyNegativeConditionWithIgnoreMissedTestNamesEnabled(
          XmlSuite xmlSuite, List<String> names) {
    boolean ignoreMissedTestNames = true;
    TestNamesMatcher testNamesHelper = new TestNamesMatcher(xmlSuite, names, ignoreMissedTestNames);
  }

  @Test
  public void testIfTestnamesComesFromDifferentSuite() {
    XmlSuite parentSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    parentSuite.setName("parent_suite");
    XmlSuite childSuite = createDummySuiteWithTestNamesAs("test3", "test4");
    childSuite.setName("child_suite");
    parentSuite.getChildSuites().add(childSuite);
    XmlSuite childOfChildSuite = createDummySuiteWithTestNamesAs("test5", "test6");
    childSuite.getChildSuites().add(childOfChildSuite);
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(
            parentSuite, new ArrayList<>(Arrays.asList("test1", "test3", "test5")));
    List<String> matchedTestnames = Lists.newArrayList();
    for (XmlTest xmlTest : testNamesMatcher.getMatchedTests()) {
      matchedTestnames.add(xmlTest.getName());
    }
    assertThat(matchedTestnames).hasSameElementsAs(Arrays.asList("test1", "test3", "test5"));
  }

  @Test(
      expectedExceptions = TestNGException.class,
      expectedExceptionsMessageRegExp = "\nThe test\\(s\\) \\<\\[test3\\]\\> cannot be found.")
  public void testCloneIfContainsTestsWithNamesMatchingAnyWithoutMatch() {
    XmlSuite xmlSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(xmlSuite, Collections.singletonList("test3"));
    List<XmlSuite> clonedSuites = testNamesMatcher.getSuitesMatchingTestNames();
    if (!CollectionUtils.hasElements(clonedSuites)) {
      throw new TestNGException(
          "The test(s) <" + Collections.singletonList("test3").toString() + "> cannot be found.");
    }
  }

  @Test(
      description =
          "GITHUB-2897, No TestNGException thrown when ignoreMissedTestNames enabled and only partial of the given test names are invalid.")
  public void testNoExceptionWhenIgnoreMissedTestNamesEnabled() {
    final boolean ignoreMissedTestNames = true;
    XmlSuite xmlSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(xmlSuite, Arrays.asList("test2", "test3"), ignoreMissedTestNames);
    testNamesMatcher.validateMissMatchedTestNames();
  }

  @Test(
      description =
          "GITHUB-2897, Expected TestNGException thrown when ignoreMissedTestNames enabled and all given test names are invalid.",
      expectedExceptions = TestNGException.class,
      expectedExceptionsMessageRegExp =
          "\nThe test\\(s\\) \\<\\[test3\\]\\> cannot be found in suite.")
  public void testHaveExceptionWhenIgnoreMissedTestNamesEnabledWithAllInvalidTestNames() {
    final boolean ignoreMissedTestNames = true;
    XmlSuite xmlSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(xmlSuite, Collections.singletonList("test3"), ignoreMissedTestNames);
    testNamesMatcher.validateMissMatchedTestNames();
  }

  @Test(
      description =
          "GITHUB-2897, Expected TestNGException thrown when ignoreMissedTestNames disabled and all given test names are invalid.",
      expectedExceptions = TestNGException.class,
      expectedExceptionsMessageRegExp =
          "\nThe test\\(s\\) \\<\\[test3\\]\\> cannot be found in suite.")
  public void testHaveExceptionWhenIgnoreMissedTestNamesDisabledWithAllInvalidTestNames() {
    final boolean ignoreMissedTestNames = false;
    XmlSuite xmlSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(xmlSuite, Collections.singletonList("test3"), ignoreMissedTestNames);
    testNamesMatcher.validateMissMatchedTestNames();
  }

  @Test(
      description =
          "GITHUB-2897, Expected TestNGException thrown when ignoreMissedTestNames disabled and partial of given test names are invalid.",
      expectedExceptions = TestNGException.class,
      expectedExceptionsMessageRegExp =
          "\nThe test\\(s\\) \\<\\[test3\\]\\> cannot be found in suite.")
  public void testHaveExceptionWhenIgnoreMissedTestNamesDisabledWithPartialInvalidTestNames() {
    final boolean ignoreMissedTestNames = false;
    XmlSuite xmlSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(xmlSuite, Arrays.asList("test2", "test3"), ignoreMissedTestNames);
    testNamesMatcher.validateMissMatchedTestNames();
  }

  @Test(description = "GITHUB-2897, Missed test names are found as expected.")
  public void testMissedTestNamesFound() {
    XmlSuite xmlSuite = createDummySuiteWithTestNamesAs("test1", "test2");
    final String expectedMissedTestNames = "test3";
    TestNamesMatcher testNamesMatcher =
        new TestNamesMatcher(xmlSuite, Collections.singletonList(expectedMissedTestNames));
    List<String> missedTestNames = testNamesMatcher.getMissedTestNames();
    assertThat(missedTestNames).hasSameElementsAs(Arrays.asList(expectedMissedTestNames));
  }

  @DataProvider(name = "getTestnames")
  public Object[][] getTestnameToSearchFor() {
    return new Object[][] {
      {"test4", false, false},
      {"test1", true, false},
      {"test5", false, true}
    };
  }

  @DataProvider(name = "getData")
  public Object[][] getTestData() {
    return new Object[][] {
      {new XmlSuite(), null},
      {new XmlSuite(), Collections.<String>emptyList()},
      {new XmlSuite(), Collections.singletonList("")},
      {new XmlSuite(), Collections.singletonList(" ")},
      {new XmlSuite(), Arrays.asList(null, "", " ")}
    };
  }
}
