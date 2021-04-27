package tech.quilldev.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        var player = event.getPlayer();
        var serverName = "DWorld";
        event.quitMessage(
                Component.empty()
                        .append(Component.text(player.getName() + " has left " + serverName + "."))
        );
    }
}
