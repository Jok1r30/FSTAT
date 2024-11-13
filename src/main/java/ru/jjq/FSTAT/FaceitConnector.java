package ru.jjq.FSTAT;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.jjq.FSTAT.entity.Player;
import ru.jjq.FSTAT.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

public class FaceitConnector {

    private static FaceitConnector instance;

    public String faceit_api = "https://open.faceit.com/data/v4";
    public String players = "/players";
    public String stats_of_cs2 = "/games/cs2/stats";

    public Player getPlayer(String name) {
        while (true) {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) IOUtils.newConnection(faceit_api + players + "?nickname=" + name);
                int response_code = urlConnection.getResponseCode();
                if(response_code / 100 != 2) {
                    break;
                }

                String body = IOUtils.readInputStream(urlConnection.getInputStream());
                JsonObject asJsonObject = JsonParser.parseString(body).getAsJsonObject();
                System.out.println("Player: " + asJsonObject.toString());
                return new Player(asJsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Player();
    }


    public Player getPlayerStats(Player player) {
        while (true) {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) IOUtils.newConnection(faceit_api + players + "/" + player.player_id + stats_of_cs2);
                int response_code = urlConnection.getResponseCode();
                if(response_code / 100 != 2) {
                    break;
                }

                String body = IOUtils.readInputStream(urlConnection.getInputStream());
                JsonObject asJsonObject = JsonParser.parseString(body).getAsJsonObject();
                System.out.println("Stats CS2: " + asJsonObject);
                return player;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Player();
    }


    public static FaceitConnector get() {
        if(instance == null) {
            instance = new FaceitConnector();
        }

        return instance;
    }
}
