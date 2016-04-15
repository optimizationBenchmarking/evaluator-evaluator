package test.junit.org.optimizationBenchmarking.evaluator.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Ignore;
import org.optimizationBenchmarking.evaluator.Main;
import org.optimizationBenchmarking.utils.io.EArchiveType;
import org.optimizationBenchmarking.utils.io.paths.PathUtils;
import org.optimizationBenchmarking.utils.io.paths.TempDir;
import org.optimizationBenchmarking.utils.text.TextUtils;

/** The base class for testing evaluators. */
@Ignore
public class EvaluatorMainTest {

  /** create */
  protected EvaluatorMainTest() {
    super();
  }

  /**
   * Run a test case
   *
   * @param configXMLResource
   *          the name of the configuration resource
   * @param evaluationXMLResource
   *          the name of the evaluation resource
   * @param dataZipResourceClass
   *          the class used to resolve the zip folder with the data
   * @param dataZipResource
   *          the name of the data
   */
  protected void runTest(final String configXMLResource,
      final String evaluationXMLResource,
      final Class<?> dataZipResourceClass, final String dataZipResource) {
    this.runTest(this.getClass(), configXMLResource, this.getClass(),
        evaluationXMLResource, dataZipResourceClass, dataZipResource);
  }

  /**
   * Run a test case
   *
   * @param configXMLResourceClass
   *          the class used to resolve the configuration resource
   * @param configXMLResource
   *          the name of the configuration resource
   * @param evaluationXMLResourceClass
   *          the class used to resolve the evaluation resource
   * @param evaluationXMLResource
   *          the name of the evaluation resource
   * @param dataZipResourceClass
   *          the class used to resolve the zip folder with the data
   * @param dataZipResource
   *          the name of the data
   */
  protected void runTest(final Class<?> configXMLResourceClass,
      final String configXMLResource,
      final Class<?> evaluationXMLResourceClass,
      final String evaluationXMLResource,
      final Class<?> dataZipResourceClass, final String dataZipResource) {
    final Path configFile, evaluationFile, configFolder, dataFolder;
    try {
      try (final TempDir tempDir = new TempDir()) {
        configFolder = PathUtils.createPathInside(tempDir.getPath(),
            "evaluation");//$NON-NLS-1$
        Files.createDirectories(configFolder);
        configFile = PathUtils.createPathInside(configFolder,
            "config.xml");//$NON-NLS-1$
        try (final InputStream is = configXMLResourceClass
            .getResourceAsStream(configXMLResource)) {
          EvaluatorMainTest.__copyConfig(is, configFile, configFolder);
        }
        evaluationFile = PathUtils.createPathInside(configFolder,
            "evaluation.xml");//$NON-NLS-1$
        try (final InputStream is = evaluationXMLResourceClass
            .getResourceAsStream(evaluationXMLResource)) {
          Files.copy(is, evaluationFile);
        }
        dataFolder = PathUtils.createPathInside(tempDir.getPath(),
            "results");//$NON-NLS-1$
        try (final InputStream is = dataZipResourceClass
            .getResourceAsStream(dataZipResource)) {
          EArchiveType.ZIP.decompressStreamToFolder(is, dataFolder, null);
        }
        Assert.assertEquals(0, Main.run(new String[] { "configXML=" + //$NON-NLS-1$
            PathUtils.getPhysicalPath(configFile, false) }));
      }
    } catch (final Throwable error) {
      throw new AssertionError("Test failed due to unexpected error", //$NON-NLS-1$
          error);
    }
  }

  /**
   * copy a configuration, fixing base paths
   *
   * @param input
   *          the input stream
   * @param dest
   *          the destination path
   * @param resolveAgainst
   *          the path to resolve against
   * @throws IOException
   *           if i/o fails
   */
  private static final void __copyConfig(final InputStream input,
      final Path dest, final Path resolveAgainst) throws IOException {
    String line;
    int startIndex, endIndex;

    try (final OutputStream output = PathUtils.openOutputStream(dest)) {
      try (final OutputStreamWriter writer = new OutputStreamWriter(
          output)) {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(
            writer)) {
          try (final InputStreamReader reader = new InputStreamReader(
              input)) {
            try (final BufferedReader bufferedReader = new BufferedReader(
                reader)) {
              while ((line = bufferedReader.readLine()) != null) {
                line = TextUtils.prepare(line);
                if (line != null) {

                  resolve: {
                    endIndex = Integer.MIN_VALUE;
                    startIndex = line.indexOf(".."); //$NON-NLS-1$
                    if (startIndex > 0) {
                      switch (line.charAt(startIndex - 1)) {
                        case '(': {
                          endIndex = line.indexOf(')', startIndex);
                          break;
                        }
                        case '"': {
                          endIndex = line.indexOf('"', startIndex);
                          break;
                        }
                        default: {
                          break resolve;
                        }
                      }
                    } else {
                      startIndex = line.indexOf("path(");//$NON-NLS-1$
                      if (startIndex > 0) {
                        startIndex += 5;
                        endIndex = line.indexOf(')', startIndex);
                      }
                    }

                    if (endIndex > startIndex) {
                      line = line.substring(0, startIndex) + //
                          PathUtils.normalize(resolveAgainst.resolve(//
                              line.substring(startIndex, endIndex)))
                          + //
                          line.substring(endIndex);
                    }
                  }
                  bufferedWriter.write(line);
                  bufferedWriter.newLine();
                }
              }
            }
          }
        }
      }
    }
  }
}
