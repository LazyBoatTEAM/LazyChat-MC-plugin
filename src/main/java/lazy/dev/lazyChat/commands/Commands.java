package lazy.dev.lazyChat.commands;

import lazy.dev.lazyChat.LazyChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    private final LazyChat plugin;

    public Commands(LazyChat plugin) {
        this.plugin = plugin;
    }

    public void register() {
        plugin.getCommand("lazy-chat").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("l-chat.*")) {
            sender.sendMessage(Component.text("No permission", NamedTextColor.RED));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Component.text("Usage: /lazy-chat <reload|info>", NamedTextColor.YELLOW));
            return true;
        }

        MiniMessage mm = MiniMessage.miniMessage();
        String action = args[0];

        if (action.equals("reload")) {
            try {
                plugin.reloadPluginConfig();
                sender.sendMessage(Component.text("LazyChat has successfully reloaded!", NamedTextColor.GREEN));
            } catch (Exception e) {
                sender.sendMessage(Component.text("LazyChat has meet error while reloading: " + e.getLocalizedMessage(), NamedTextColor.RED));
                plugin.getLogger().severe("Plugin has found error while reloading: " + e.getMessage());
            }
        } else if (action.equals("info")) {
            Component infoMessage = mm.deserialize("<color:#70c4ff>Lazy-Plugin \"LazyChat\"</color>\n" +
                    "<color:#70c4ff>Author of plugin: LazyCato0o</color> (<click:open_url:'https://ru.namemc.com/profile/LazyCato0o.1'>NameMC</click>)\n" +
                    "<color:#70c4ff>Available on</color> <click:open_url:'https://github.com/LazyCat0/LazyChat-MC-plugin'><color:#e761ff>Github</color></click>, <click:open_url:'https://www.spigotmc.org/resources/lazychat.130059/'><color:#fff757>SpigotMC</color></click>.");
            sender.sendMessage(infoMessage);
        } else {
            sender.sendMessage(mm.deserialize("<b><red>Unknown argument, correct is \"info\" and \"reload\"</red></b>"));
        }

        return true;
    }
}
