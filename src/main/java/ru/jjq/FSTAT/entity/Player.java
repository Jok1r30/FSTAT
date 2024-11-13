package ru.jjq.FSTAT.entity;

import com.google.gson.JsonObject;

public class Player {

    public String player_id;

    public String nickname;
    public String country;

    public String avatar;
    public String cover_image;

    public Player() {

    }

    public Player(JsonObject jsonObject) {
        player_id = jsonObject.get("player_id").getAsString();

        nickname = jsonObject.get("nickname").getAsString();
        country = jsonObject.get("country").getAsString();

        avatar = jsonObject.get("avatar").getAsString();
        cover_image = jsonObject.get("cover_image").getAsString();
    }
}
