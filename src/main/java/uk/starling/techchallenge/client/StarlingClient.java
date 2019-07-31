package uk.starling.techchallenge.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import uk.starling.techchallenge.model.StarlingRoundUpRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;

@Component
public class StarlingClient {
    public enum HttpMethod {
        POST,
        GET,
        PUT
    }

    private ObjectMapper mapper;
    private String accesToken;

    public StarlingClient() {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//
    }

    public <T> T sendRequest(URL url,
                             HttpMethod method,
                             StarlingRoundUpRequest request,
                             Class<T> classType) throws IOException {
        final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        try {
            connection.setRequestMethod(method.name());
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Authorization", accesToken);

            //Verify if we can send (output) a request body.
            if (method == HttpMethod.POST || method == HttpMethod.PUT) {
                connection.setDoOutput(true);
                String serializedBody = serializeObject(request);
                try (OutputStream os = connection.getOutputStream(); DataOutputStream dos = new DataOutputStream(os)) {
                    dos.write(serializedBody.getBytes(UTF_8));
                    dos.flush();
                }
            }
            return getResponse(connection, classType);
        } finally {
            connection.disconnect();
        }
    }

    private <T> T getResponse(HttpsURLConnection connection, Class<T> classType) throws IOException {
        try (InputStream is = connection.getInputStream()) {
            String body = new String(is.readAllBytes(), UTF_8);
            return mapper.readValue(body, classType);
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e) {
            try (InputStream is = connection.getErrorStream()) {
                if (nonNull(is)) {
                    throw new RuntimeException(connection.getResponseMessage());
                } else {
                    throw e;
                }
            }
        }
    }

    String serializeObject(StarlingRoundUpRequest request) throws JsonProcessingException {
        Map<String, Object> requestWithCredentials = mapper.convertValue(request, Map.class);
        return mapper.writeValueAsString(requestWithCredentials);
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }
}
