package org.optimizationBenchmarking.evaluator;

import java.util.logging.Logger;

import org.optimizationBenchmarking.evaluator.data.spec.IExperiment;
import org.optimizationBenchmarking.evaluator.data.spec.IExperimentSet;
import org.optimizationBenchmarking.evaluator.evaluation.spec.IEvaluationJob;
import org.optimizationBenchmarking.utils.document.spec.IPlainText;
import org.optimizationBenchmarking.utils.document.spec.ISectionBody;

/** The holder for the a single experiment statistic */
final class _ExperimentJob extends _PartJob {

  /** the experiment */
  private final IExperiment m_experiment;

  /**
   * create the single experiment statistics job
   *
   * @param experiment
   *          the experiment
   * @param data
   *          the data
   * @param logger
   *          the logger
   * @param children
   *          the children
   */
  _ExperimentJob(final IExperiment experiment, final IExperimentSet data,
      final Logger logger, final IEvaluationJob[] children) {
    super(data, logger, children);
    if (experiment == null) {
      throw new IllegalArgumentException(//
          "Experiment cannot be null.");//$NON-NLS-1$
    }
    this.m_experiment = experiment;
  }

  /** {@inheritDoc} */
  @Override
  final String _getName() {
    return ("Single-Experiment Statistics for " //$NON-NLS-1$
        + this.m_experiment.getName());
  }

  /** {@inheritDoc} */
  @Override
  final void _writeSectionIntroduction(final IExperimentSet data,
      final ISectionBody body) {
    //
  }

  /** {@inheritDoc} */
  @Override
  final void _writeSectionTitle(final IExperimentSet data,
      final IPlainText title) {
    title.append("Performance Evaluation for "); //$NON-NLS-1$
    title.append(this.m_experiment.getName());
  }

  /** {@inheritDoc} */
  @Override
  int _getMinSectionsToOpenNewSection() {
    return 1;
  }
}
