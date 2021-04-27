package tech.quilldev.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.quilldev.Constants;

public class onLeave implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        var player = event.getPlayer();

        //Leave components
        var serverName = Component.text(Constants.WORLD_NAME).style(Style.style(TextDecoration.ITALIC));
        var leaveIcon = Component.text("-").style(Style.style(TextDecoration.BOLD));

        event.quitMessage(
                Component.empty()
                        .append(leaveIcon)
                        .append(Component.text(" " + player.getName() + " has left "))
                        .append(serverName)
                        .color(TextColor.color(255, 79, 63))
        );
    }
}
