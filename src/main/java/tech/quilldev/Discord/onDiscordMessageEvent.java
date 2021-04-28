package tech.quilldev.Discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;


public class onDiscordMessageEvent extends ListenerAdapter {

    private final String channelId;

    public onDiscordMessageEvent(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (!event.getChannel().getId().equals(channelId)) return;

        String author = event.getMessage().getAuthor().getName();


        final var discordTag = Component.text("[DISCORD]")
                .append(Component.text(" "))
                .color(TextColor.color(185, 44, 255));

        final var minecraftContent = Component.text(author)
                .append(Component.text(": "))
                .append(Component.text(event.getMessage().getContentDisplay()))
                .color(TextColor.color(210, 210, 210));


        Bukkit.getServer().sendMessage(discordTag.append(minecraftContent));
    }
}
