package fr.lernejo.navy_battle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class ServerInformation {
    @NotNull
    private final String id;
    @NotNull
    private final String url;
    @NotNull
    private final String message;

    @JsonCreator
    public ServerInformation(@JsonProperty("id") String id,
                      @JsonProperty("url") String url,
                      @JsonProperty("message") String message) {
        this.id = id;
        this.url = url;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
