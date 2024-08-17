package groups.venixteam.laas;

import groups.venixteam.laas.cmd.GroupCommand;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §f...");
    }

    @Override
    public void onEnable() {
        getCommand("group").setExecutor(new GroupCommand());
        Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §fRecursos carregados com sucesso.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §cEncerrando recursos.");
    }

    public static Main getInstance() {
        return instance;
    }
}