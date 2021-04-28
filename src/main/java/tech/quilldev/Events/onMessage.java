package tech.quilldev.Events;

import io.papermc.paper.event.player.AsyncChatEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tech.quilldev.Database.DatabaseManager;

public class onMessage implements Listener {

    @EventHandler
    public void onMessageEvent(AsyncChatEvent event) {
        event.setCancelled(true);

        var player = event.getPlayer();

        final var user = DatabaseManager.getStratumUser(player);

        assert user != null;
        final var rank = user.getRank();

        //Setup Message components
        final var rankTag =
                Component.text("[" + rank.label + "]")
                        .color(user.getRank().color);

        final var nameTag =
                Component.text(player.getName())
                        .color(TextColor.color(235, 252, 255));

        final var message =
                Component.text(':')
                        .append(Component.space())
                        .append(event.message())
                        .color(TextColor.color(235, 252, 255));

        player.getServer().sendMessage(
                rankTag
                        .append(Component.space())
                        .append(nameTag)
                        .append(message)
        );

    }
}
