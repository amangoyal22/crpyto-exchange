package com.finance.crpyto.utils;

import com.finance.crpyto.exceptions.CrpytoException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.asynchttpclient.util.HttpConstants;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * The type Rest utils.
 */
@Slf4j
@Component
public class RestUtils {

  /**
   * The Retry template.
   */
  private final RetryTemplate retryTemplate;

  /**
   * Instantiates a new Rest utils.
   *
   * @param retryTemplate the retry template
   */
  public RestUtils(final RetryTemplate retryTemplate) {
    this.retryTemplate = retryTemplate;
  }

  /**
   * Execute with retry response.
   *
   * @param request         the request
   * @param asyncHttpClient the async http client
   * @return the response
   */
  public Response executeWithRetry(final Request request, final AsyncHttpClient asyncHttpClient) {
    try {
      return retryTemplate.execute(retryContext -> {
        final var response = asyncHttpClient.executeRequest(request).get();
        if (!validateResponse(response)) {
          log.error("error in rest call request {} response {}", request.getUrl(), response);
          throw new CrpytoException("error in rest call ");
        }
        return response;
      });
    } catch (final Exception exception) {
      log.error("error calling endpoint {}", exception.getMessage());
      throw new CrpytoException("error calling rest service");
    }
  }

  /**
   * Execute response.
   *
   * @param request         the request
   * @param asyncHttpClient the async http client
   * @return the response
   * @throws ExecutionException   the execution exception
   * @throws InterruptedException the interrupted exception
   */
  public Response execute(final Request request, final AsyncHttpClient asyncHttpClient)
      throws ExecutionException, InterruptedException {

    final var response = asyncHttpClient.executeRequest(request).get();

    if (!validateResponse(response)) {
      log.debug("error in rest call request {} response {}", request.getUrl(), response);
      throw new CrpytoException("error in rest call ");
    }

    return response;
  }

  /**
   * Validate response boolean.
   *
   * @param response the response
   * @return the boolean
   */
  private boolean validateResponse(final Response response) {
    return Objects.nonNull(response)
        && response.getStatusCode() == HttpConstants.ResponseStatusCodes.OK_200
        && response.hasResponseBody();
  }

}
