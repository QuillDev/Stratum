package tech.quilldev.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LinkItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        final var player = ((Player) sender).getPlayer();

        //Handle bad arguments
        assert player != null;

        final var item = player.getInventory().getItemInMainHand();

        //Make it so they can't link air
        if (item.getType().equals(Material.AIR)) {
            player.sendMessage("You cannot link air!");
            return true;
        }

        //Get the name of the item
        var customName = item.getItemMeta().displayName();
        if (customName == null) {
            customName = Component.text(Objects.requireNonNull(item.getI18NDisplayName()));
        }

        final var meta = item.getItemMeta();
        var loreComponent = Component.empty();

        //Add item lore
        if (meta.hasLore()) {
            for (Component lore : Objects.requireNonNull(item.lore())) {
                loreComponent = loreComponent.append(lore.append(Component.newline()));
            }
        }

        final var enchants = item.getEnchantments();
        var enchantComponent = Component.empty();
        for (final var enchant : enchants.keySet()) {
            final var lvl = item.getEnchantmentLevel(enchant);
            final var name = enchant.displayName(lvl);
            enchantComponent = enchantComponent
                    .append(name)
                    .append(Component.newline())
            ;
        }

        final var message =
                Component.text(player.getName() + " presents ")
                        .append(Component.text("["))
                        .append(customName)
                        .append(Component.text("]"))
                        .hoverEvent(HoverEvent.showText(
                                customName
                                        .append(Component.newline())
                                        .append(enchantComponent)
                                        .append(loreComponent)
                        ));
        Bukkit.getServer().sendMessage(message);
        return true;
    }
}
