package tech.quilldev.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        var serverName = "DWorld";
        var comp = Component.text("Welcome to ")
                .append(Component.text(serverName))
                .append(Component.text(" "))
                .append(Component.text(player.getName()))
                .color(TextColor.color(255, 0, 0));
        event.joinMessage(comp);

    }

}
