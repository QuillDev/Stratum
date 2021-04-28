package tech.quilldev.Discord;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class onDiscordReadyEvent extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent ignored) {
        System.out.println("Bot started and ready!");
    }
}
