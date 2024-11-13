package ru.jjq.FSTAT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jjq.FSTAT.FaceitConnector;
import ru.jjq.FSTAT.entity.Player;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @GetMapping(value = "/{name}")
    public String player(@PathVariable(name = "name") String name, Model model) {
        Player player = FaceitConnector.get().getPlayer(name);
        player = FaceitConnector.get().getPlayerStats(player);
        model.addAttribute("nickname", player.nickname);
        model.addAttribute("avatar", player.avatar);
        model.addAttribute("cover_image", player.cover_image);
        return "player";
    }

}
