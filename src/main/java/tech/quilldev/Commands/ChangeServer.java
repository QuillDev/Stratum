package tech.quilldev.Commands;

import org.bukkit.Bukkit;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tech.quilldev.Helpers.ConfigManager;


public class ChangeServer implements CommandExecutor {

    private final ConfigManager configManager;

    public ChangeServer(ConfigManager manager) {
        this.configManager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (final var arg : args) {
            System.out.println(arg);
        }
        if (args.length <= 0) return false;

        //If the layout is [player] [world]
        if (args.length == 2) {
            if (sender instanceof CommandBlock) {
                System.out.println("COMMAND AHFUJSGASJGJGIOUS");
                var players = ((CommandBlock) sender).getLocation().getNearbyPlayers(20);
                if (!players.isEmpty()) {
                    System.out.println(players.size());
                }
                System.out.println("PLAYER TRIGGERED");
            }
            var target = Bukkit.getPlayer(args[0]);
            if (target == null) return false;
            System.out.println(target.displayName());

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
