package groups.venixteam.laas;

import groups.venixteam.laas.cmd.GroupCommand;
import groups.venixteam.laas.cmd.TagCommand;
import groups.venixteam.laas.database.MySQL;
import groups.venixteam.laas.database.ProfileTable;
import groups.venixteam.laas.listeners.PlayerChatListener;
import groups.venixteam.laas.listeners.PlayerJoinListener;
import groups.venixteam.laas.tag.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin {

    private static Main instance;
    private static TagManager tagManager;
    private static MySQL database;
    private static ProfileTable table;

    @Override
    public void onLoad() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §fPlugin carregando...");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        Language.load();

        String host = getConfig().getString("database.host");
        int port = getConfig().getInt("database.port");
        String db = getConfig().getString("database.db");
        String user = getConfig().getString("database.user");
        String pass = getConfig().getString("database.pass");


        database = new MySQL(host, port, db, user, pass);
        try {
            database.connect();
            tagManager = new TagManager(getConfig().getConfigurationSection("tags"));
            table = new ProfileTable(database.getConnection(), tagManager);
            table.createTable();
            Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §aConexão com o MySQL estabelecida.");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §cErro ao conectar no MySQL.");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("group").setExecutor(new GroupCommand());
        getCommand("tag").setExecutor(new TagCommand(tagManager));

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);

        Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §aPlugin habilitado com sucesso.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§6[venix-groups] §cPlugin desativado.");
    }

    public static Main getInstance() {
        return instance;
    }

    public static TagManager getTagManager() {
        return tagManager;
    }

    public static ProfileTable getProfileTable() {
        return table;
    }
}
