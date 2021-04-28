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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.quilldev.Config.ConfigManager;
import tech.quilldev.Constants;

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
        final var msg = (TextComponent) chatEvent.message();
        final var name = chatEvent.getPlayer().getName();
        this.channel.sendMessage(name + ": " + msg.content()).queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent chatEvent) {
        final var name = chatEvent.getPlayer().getName();
        this.channel.sendMessage(":wave: " + name + " has **left** " + Constants.WORLD_NAME + "!").queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerJoinEvent chatEvent) {
        final var name = chatEvent.getPlayer().getName();
        this.channel.sendMessage(":wave: " + name + " has **joined** " + Constants.WORLD_NAME + "!").queue();
    }

    /**
     * Shutdown the bot
     */
    public void close() {
        assert bot != null;
        this.bot.shutdownNow();
    }
}
