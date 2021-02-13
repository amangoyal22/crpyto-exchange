package com.finance.crpyto.service;

import com.finance.crpyto.config.EmailConfig;
import com.finance.crpyto.constant.EmailConstantUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Email service.
 */
@Component
@Slf4j
@AllArgsConstructor
public class EmailService {

  /**
   * The Email config.
   */
  private final EmailConfig emailConfig;

  /**
   * Send mail.
   *
   * @param from    the from
   * @param to      the to
   * @param subject the subject
   * @param body    the body
   */
  public void sendMail(final String from,
                       final String to,
                       final String subject,
                       final String body) {

    try {
      final var data =
          EmailConstantUtils.API_KEY
              .concat(URLEncoder.encode(emailConfig.getApiKey(), EmailConstantUtils.ENCODING))
              .concat(EmailConstantUtils.FROM).concat(URLEncoder.encode(from, EmailConstantUtils.ENCODING))
              .concat(EmailConstantUtils.TO).concat(URLEncoder.encode(to, EmailConstantUtils.ENCODING))
              .concat(EmailConstantUtils.SUBJECT).concat(URLEncoder.encode(subject, EmailConstantUtils.ENCODING))
              .concat(EmailConstantUtils.BODY).concat(URLEncoder.encode(body, EmailConstantUtils.ENCODING))
              .concat(EmailConstantUtils.TRANSACTIONAL)
              .concat(URLEncoder.encode(Boolean.TRUE.toString(), EmailConstantUtils.ENCODING));

      final var conn = new URL(emailConfig.getUrl()).openConnection();
      conn.setDoOutput(Boolean.TRUE);
      final var wr = new OutputStreamWriter(conn.getOutputStream());
      wr.write(data);
      wr.flush();
      final var rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      final var result = rd.readLine();
      wr.close();
      rd.close();
      log.info("Response of Email Service : {}", result);
    } catch (final Exception exp) {
      log.error("Error while Sending Email {}", exp.getStackTrace());
    }
  }
}
