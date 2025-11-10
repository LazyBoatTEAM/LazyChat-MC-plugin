package lazy.dev.lazyChat;

import lazy.dev.lazyChat.chatSystem.ChatUtility;
import lazy.dev.lazyChat.chatSystem.lcManager;
import lazy.dev.lazyChat.commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;

public final class LazyChat extends JavaPlugin {
    public static LazyChat instance;
    private ChatUtility chatManager;
    public Commands commands;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.chatManager = new ChatUtility(this);

        getServer().getPluginManager().registerEvents(new lcManager(this), this);
        commands.register();
    }
    public ChatUtility getChatUtility() {
        return chatManager;
    }
    public void reloadPluginConfig() {
        reloadConfig();
        chatManager.reloadConfig();
    }
}
