package org.optimizationBenchmarking.evaluator;

import java.util.logging.Logger;

import org.optimizationBenchmarking.evaluator.data.spec.IExperimentSet;
import org.optimizationBenchmarking.evaluator.evaluation.spec.IEvaluationBuilder;
import org.optimizationBenchmarking.evaluator.evaluation.spec.IEvaluationInput;
import org.optimizationBenchmarking.evaluator.evaluation.spec.IEvaluationModule;
import org.optimizationBenchmarking.evaluator.evaluation.spec.IEvaluationOutput;
import org.optimizationBenchmarking.utils.bibliography.data.BibAuthors;
import org.optimizationBenchmarking.utils.config.Configuration;
import org.optimizationBenchmarking.utils.document.spec.IDocument;
import org.optimizationBenchmarking.utils.tools.spec.IFileProducerListener;
import org.optimizationBenchmarking.utils.tools.spec.IRunnableToolJob;

/** The evaluation builder */
final class _EvaluationBuilder extends _EvaluationSetup
    implements IEvaluationBuilder {

  /** build the evaluation procedure */
  _EvaluationBuilder() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setBaseConfiguration(
      final Configuration config) {
    this._setBaseConfiguration(config);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder addModule(final IEvaluationModule module,
      final Configuration config) {
    this._addModule(module, config);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setInputData(final IExperimentSet data) {
    this._setInputData(data);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setInput(final IEvaluationInput data) {
    this._setInput(data);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setOutputDocument(final IDocument doc) {
    this._setOutputDocument(doc);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setOutput(
      final IEvaluationOutput output) {
    this._setOutput(output);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setAuthors(final BibAuthors authors) {
    this._setAuthors(authors);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setLogger(final Logger logger) {
    this._setLogger(logger);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder configure(final Configuration config) {
    this._setConfiguration(config);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final IRunnableToolJob create() {
    return new _Evaluation(this);
  }

  /** {@inheritDoc} */
  @Override
  public final IEvaluationBuilder setFileProducerListener(
      final IFileProducerListener listener) {
    this._setFileProducerListener(listener);
    return this;
  }
}
