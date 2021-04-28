package tech.quilldev.Discord;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.Compression;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import tech.quilldev.Config.ConfigManager;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class TsubasaStratum implements Listener {

    private final JDA bot;
    private final TextChannel channel;

    public TsubasaStratum(ConfigManager configManager) throws LoginException, InterruptedException {
        final var token = Objects.requireNonNull(configManager.getConfig().get("discordConfig.discordToken")).toString();
        final var channelId = Objects.requireNonNull(configManager.getConfig().get("discordConfig.channelId")).toString();

        this.bot = JDABuilder.createDefault(token)
                .setCompression(Compression.ZLIB)
                .addEventListeners(new onDiscordMessageEvent(channelId), new onDiscordReadyEvent())
                .build();
        this.bot.awaitReady();

        this.channel = this.bot.getTextChannelById(channelId);
    }

    @EventHandler
    public void onGameChat(AsyncChatEvent chatEvent) {
        //hatEvent.message().
        final var msg = (TextComponent) chatEvent.message();
        final var name = chatEvent.getPlayer().getName();
        this.channel.sendMessage(name + ": " + msg.content()).queue();
    }
}
