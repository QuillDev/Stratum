package tech.quilldev.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChangeServer implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length <= 0) return false;

        //If the layout is [player] [world]
        if (args.length == 2) {

            var target = Bukkit.getPlayer(args[0]);
            if (target == null) return false;

            var world = Bukkit.getWorld(args[1]);
            if (world == null) return false;

            target.teleport(world.getSpawnLocation());
        }

        if (args.length == 1) {
            if (!(sender instanceof Player)) return false;
            var player = ((Player) sender).getPlayer();

            var world = Bukkit.getWorld(args[0]);
            assert player != null;
            assert world != null;
            player.teleport(world.getSpawnLocation());
        }
        return false;
    }
}
