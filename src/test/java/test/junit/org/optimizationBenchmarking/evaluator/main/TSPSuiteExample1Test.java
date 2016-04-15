package test.junit.org.optimizationBenchmarking.evaluator.main;

import org.junit.Test;

import examples.org.optimizationBenchmarking.evaluator.dataAndIO.TSPSuiteExample;

/** the test based on the TSP Suite example */
public class TSPSuiteExample1Test extends EvaluatorMainTest {

  /** create */
  public TSPSuiteExample1Test() {
    super();
  }

  /** run the test */
  @Test(timeout = 3600000)
  public void testSigAlternate() {
    this.runTest("configTestSuiteTest1LaTeXSigAlternate.xml", //$NON-NLS-1$
        "evaluationTestSuiteTest1.xml", //$NON-NLS-1$
        TSPSuiteExample.class, TSPSuiteExample.RESOURCE_NAME);
  }
}
