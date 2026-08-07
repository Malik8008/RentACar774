package az.rentcar.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Setter;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Setter
public class TelegramAppender extends AppenderBase<ILoggingEvent> {

    private String botToken;
    private String chatId;

    private final RestClient restClient = RestClient.create();


    @Override
    protected void append(ILoggingEvent event) {

        if (event.getLevel().isGreaterOrEqual(Level.ERROR)) {

            String message = buildMessage(event);

            boolean silent = event.getMarkerList() != null &&
                    event.getMarkerList().stream()
                            .anyMatch(marker -> "SILENT".equals(marker.getName()));

            sendTelegram(message,silent);
        }
    }


    private String buildMessage(ILoggingEvent event) {

        StringBuilder sb = new StringBuilder();

        sb.append("🚨 RENTCAR ERROR\n\n");
        sb.append(event.getFormattedMessage());

        if (event.getThrowableProxy() != null) {

            sb.append("\n\nException: ")
                    .append(event.getThrowableProxy().getClassName());

            sb.append("\nReason: ")
                    .append(event.getThrowableProxy().getMessage());
        }

        return sb.toString();
    }


    private void sendTelegram(String text, boolean silent) {

        String url = "https://api.telegram.org/bot"
                + botToken
                + "/sendMessage";

        restClient.post()
                .uri(url)
                .body(Map.of(
                        "chat_id", chatId,
                        "text", text,
                        "disable_notification", silent
                ))
                .retrieve()
                .toBodilessEntity();
    }
}
