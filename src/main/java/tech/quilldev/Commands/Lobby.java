package tech.quilldev.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.SplittableRandom;

public class Lobby implements CommandExecutor {
    private final ChangeServer changeServer;
    public Lobby(ChangeServer changeServer){
        this.changeServer = changeServer;
    }





    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        changeServer.onCommand(commandSender, command, s,new String[] {"world"});
        return false;
    }

}
