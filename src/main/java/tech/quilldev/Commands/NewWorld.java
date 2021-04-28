package tech.quilldev.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NewWorld implements CommandExecutor {

    //TODO: Add permissions to this ftlog
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length <= 0) return false;

        var worldCreator = new WorldCreator(args[0]);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.NORMAL);

        worldCreator.createWorld();

        var player = ((Player) sender).getPlayer();
        if (player == null) return false;
        Bukkit.getServer().sendMessage(Component.text(player.getName() + " created a new world " + args[0]));
        return true;
    }
}
