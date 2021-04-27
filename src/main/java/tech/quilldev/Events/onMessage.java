package tech.quilldev.Events;

import io.papermc.paper.event.player.AsyncChatEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onMessage implements Listener {

    @EventHandler
    public void onMessageEvent(AsyncChatEvent event) {
        event.setCancelled(true);

        var player = event.getPlayer();
        var tag = Component.text("[" + player.getName() + "] ").color(TextColor.color(0, 0, 255));

        player.getServer().sendMessage(
                Component.empty()
                        .append(tag)
                        .append(event.message())
        );

    }
}
