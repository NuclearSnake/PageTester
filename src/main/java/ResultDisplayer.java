/**
 * <h1>Common interface for displaying the testing result</h1>
 *
 * Provides uniform API for displaying the testing result
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-23
 * @see WebPageTestingResult
 */
public interface ResultDisplayer {
    /**
     * Generates {@link String} output based on the {@link WebPageTestingResult} provided
     *
     * @param webPageTestingResult to get output from
     * @return string with all the output information inside
     */
    String getResultText(WebPageTestingResult webPageTestingResult);
}
