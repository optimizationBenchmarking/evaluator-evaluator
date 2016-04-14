package org.optimizationBenchmarking.evaluator;

import org.optimizationBenchmarking.evaluator.evaluation.definition.data.EvaluationModulesBuilder;
import org.optimizationBenchmarking.evaluator.evaluation.impl.EvaluationModuleParser;
import org.optimizationBenchmarking.evaluator.evaluation.spec.IEvaluationModule;
import org.optimizationBenchmarking.utils.hierarchy.HierarchicalFSM;

/** The builder for evaluation setups. */
final class _EvaluationModulesBuilder extends EvaluationModulesBuilder {

  /** create the modules builder */
  _EvaluationModulesBuilder() {
    this(null);
  }

  /**
   * create the modules builder
   *
   * @param owner
   *          the owning FSM, or {@code null} if none was specified
   */
  _EvaluationModulesBuilder(final HierarchicalFSM owner) {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected IEvaluationModule parseModule(final String module) {
    return EvaluationModuleParser.getInstance().parseString(module);
  }
}
