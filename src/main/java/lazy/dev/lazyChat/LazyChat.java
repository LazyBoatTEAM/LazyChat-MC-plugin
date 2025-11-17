package lazy.dev.lazyChat;

import lazy.dev.lazyChat.chatSystem.ChatUtility;
import lazy.dev.lazyChat.chatSystem.lcManager;
import lazy.dev.lazyChat.commands.Commands;
import net.luckperms.api.LuckPerms;
import org.bukkit.plugin.java.JavaPlugin;

public final class LazyChat extends JavaPlugin {
    public static LazyChat instance;
    private ChatUtility chatUtility;
    private LuckPerms lp;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        this.chatUtility = new ChatUtility(this, lp);

        getServer().getPluginManager().registerEvents(new lcManager(this), this);
        new Commands(this).register();
    }
    public ChatUtility getChatUtility() {
        return chatUtility;
    }
    public void reloadPluginConfig() {
        reloadConfig();
        chatUtility.reloadConfig();
    }
}
