package lazy.dev.lazyChat.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.StringArgument;
import lazy.dev.lazyChat.LazyChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Commands {
    private final LazyChat plugin;
    public Commands(LazyChat plugin) {
        this.plugin = plugin;
    }

    public void register() {
        MiniMessage mm = MiniMessage.miniMessage();
        Component unknownArg = mm.deserialize("<b><red>Unknown argument, correct is \"info\" and \"reload\"</red></b>");
        new CommandAPICommand("lazy-chat")
                .withPermission(CommandPermission.fromString("l-chat.*"))
                .withArguments(new StringArgument("action"))
                .withAliases("l-chat", "lc")
                .executes((sender, args) -> {
                    String act = args.getRaw("action");
                    if (act.equals("reload")) {
                        try {
                            plugin.reloadPluginConfig();
                            sender.sendMessage(Component.text("LazyChat has successfully reloaded!", NamedTextColor.GREEN));
                        } catch (Exception e) {
                            sender.sendMessage(Component.text("LazyChat has meet error while reloading, error message: " + e.getLocalizedMessage(), NamedTextColor.RED));
                            plugin.getLogger().severe("Plugin has found error while reloading: " + e.getMessage());
                        }
                    }
                    if (act.equals("info")) {
                        Component infoMessage = mm.deserialize("<color:#70c4ff>Lazy-Plugin \"LazyChat\"</color>\n" +
                                "<color:#70c4ff>Author of plugin: LazyCato0o</color> (<click:open_url:'https://ru.namemc.com/profile/LazyCato0o.1'>NameMC</click>)\n" +
                                "<color:#70c4ff>Available on</color> <click:open_url:'https://github.com/LazyCat0/LazyChat-MC-plugin'><color:#e761ff>Github</color></click>, <click:open_url:'https://www.spigotmc.org/resources/lazychat.130059/'><color:#fff757>SpigotMC</color></click>.");
                        sender.sendMessage(infoMessage);
                    }
                    else {
                        sender.sendMessage(unknownArg);
                    }
                })
                .register();
    }
}