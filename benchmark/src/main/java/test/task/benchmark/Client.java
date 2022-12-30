package test.task.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import test.task.benchmark.dto.BankAccountDto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@RequiredArgsConstructor
public class Client {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String target;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Long> getBalance(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/getBalance?id=%d", target, id)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != HttpURLConnection.HTTP_OK) {
            throw new IllegalStateException(String.format("Error status %d", response.statusCode()));
        }
        BankAccountDto  result = objectMapper.readValue(response.body(), BankAccountDto.class);
        return Optional.ofNullable(result).map(BankAccountDto::getAmount);
    }

    public void changeBalance(long id, long amount) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/changeBalance?id=%d&amount=%d", target, id, amount)))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != HttpURLConnection.HTTP_OK) {
            throw new IllegalStateException(String.format("Error status %d", response.statusCode()));
        }
    }



}
