package tech.quilldev.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tech.quilldev.Constants;

public class onJoin implements Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();


        //Components
        var serverName = Component.text(Constants.WORLD_NAME).style(Style.style(TextDecoration.ITALIC));
        var joinIcon = Component.text("+").style(Style.style(TextDecoration.BOLD));

        event.joinMessage(
                Component.empty()
                        .append(joinIcon)
                        .append(Component.text((" Welcome to ")))
                        .append(serverName)
                        .append(Component.text(" "))
                        .append(Component.text(player.getName()))
                        .color(TextColor.color(23, 255, 95))
        );

        //TODO Reduce duration
        var title = Title.title(
                Component.empty()
                        .append(
                                Component.text("Welcome to " + Constants.WORLD_NAME)
                                        .style(Style.style(TextDecoration.BOLD))
                                        .color(TextColor.color(23, 255, 95))
                        )
                ,
                Component.empty()
        );

        player.showTitle(title);
    }

}
