package lazy.dev.lazyChat.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.executors.CommandExecutor;
import lazy.dev.lazyChat.LazyChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

public abstract class Commands implements CommandExecutor {
    private final LazyChat plugin;
    public final MiniMessage mm;
    public Commands(LazyChat plugin) {
        this.plugin = plugin;
        this.mm = MiniMessage.miniMessage();
    }

    public void register() {
        new CommandAPICommand("lc-reload")
                .withPermission(CommandPermission.fromString("l-chat.reload"))
                .withAliases("l-chat-rl")
                .executes((sender, args) -> {
                    try {
                        plugin.reloadPluginConfig();
                        sender.sendMessage(Component.text("LazyChat has successfully reloaded!", NamedTextColor.GREEN));
                    } catch (Exception e) {
                        sender.sendMessage(Component.text("LazyChat has meet error while reloading, error message: " + e.getLocalizedMessage(), NamedTextColor.RED));
                        plugin.getLogger().severe("Plugin has found error while reloading: " + e.getMessage());
                    }
                })
                .register();
    }
}
