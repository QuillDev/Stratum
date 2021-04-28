package tech.quilldev.Discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;

public class onDiscordMessageEvent extends ListenerAdapter {

    private final String channelId;
    public onDiscordMessageEvent(String channelId){
        this.channelId = channelId;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if(!event.getChannel().getId().equals(channelId)) return;

        //Print the content of the message
        String content = event.getMessage().getContentDisplay();
        String author = event.getMessage().getAuthor().getName();
        Bukkit.getServer().sendMessage(
                Component.empty()
                .append(Component.text("[DISCORD] ").color(TextColor.color(185, 44, 255)))
                .append(Component.text(author + ": " + content))
        );
    }
}
