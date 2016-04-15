package test.junit.org.optimizationBenchmarking.evaluator.main;

import org.junit.Test;

import examples.org.optimizationBenchmarking.evaluator.dataAndIO.TSPSuiteExample;

/** the test based on the TSP Suite example */
public class TSPSuiteExampleTest1 extends EvaluatorMainTest {

  /** create */
  public TSPSuiteExampleTest1() {
    super();
  }

  /** run the test */
  @Test(timeout = 3600000)
  public final void test() {
    this.runTest("configTestSuiteTest1LaTeXSigAlternate.xml", //$NON-NLS-1$
        "evaluationTestSuiteTest1.xml", //$NON-NLS-1$
        TSPSuiteExample.class, TSPSuiteExample.RESOURCE_NAME);
  }
}
