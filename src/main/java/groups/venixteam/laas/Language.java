package groups.venixteam.laas;

import org.bukkit.configuration.file.FileConfiguration;

public class Language {

    public static boolean enableVipTitleActive;
    public static String vipTitleHeader;
    public static String vipTitleFooter;
    public static int vipTitleFadein;
    public static int vipTitleShowtime;
    public static int vipTitleFadeout;

    public static String permissionError;

    // /group.
    public static String playerError;
    public static String playerInfoError;
    public static String playerGroupError;
    public static String playerGroupHas;
    public static String playerGroupSet;
    public static String playerGroupRemove;

    // /tag.
    public static String tagAccess;
    public static String tagAvailable;
    public static String tagError;
    public static String tagPermission;
    public static String tagSet;

    // chat format for tags
    public static String tagChatFormat;
    public static boolean tagChatEnable;

    // lobby join message for tags
    public static boolean tagLobbyEnabled;
    public static String tagLobbyFormat;

    public static void load() {
        FileConfiguration config = Main.getInstance().getConfig();

        // Title settings
        enableVipTitleActive = config.getBoolean("title.active");
        vipTitleHeader       = config.getString("title.header", "").replace("&", "§");
        vipTitleFooter       = config.getString("title.footer", "").replace("&", "§");
        vipTitleFadein       = config.getInt("title.fadein");
        vipTitleShowtime     = config.getInt("title.showtime");
        vipTitleFadeout      = config.getInt("title.fadeout");

        // Tag chat format
        tagChatFormat  = config.getString("tag.chat-format", "{TAG}{PLAYER}: {MSG}").replace("&", "§");
        tagChatEnable  = config.getBoolean("tag.enable-chat-format");

        // Tag lobby join
        tagLobbyEnabled = config.getBoolean("tag.enable-join-lobby");
        tagLobbyFormat  = config.getString("tag.lobbymessage-format", "{TAG}{PLAYER} §6entrou no lobby!").replace("&", "§");

        // Messages
        permissionError = config.getString("messages.permission", "").replace("&", "§");

        playerError      = config.getString("messages.player-error", "").replace("&", "§");
        playerInfoError  = config.getString("messages.playerinfo-error", "").replace("&", "§");
        playerGroupError = config.getString("messages.playergroup-error", "").replace("&", "§");
        playerGroupHas   = config.getString("messages.playergroup-has", "").replace("&", "§");
        playerGroupSet   = config.getString("messages.playergroup-set", "").replace("&", "§");
        playerGroupRemove= config.getString("messages.playergroup-remove", "").replace("&", "§");

        tagAccess       = config.getString("messages.tag-acess", "").replace("&", "§");
        tagAvailable    = config.getString("messages.tag-available", "").replace("&", "§");
        tagError        = config.getString("messages.tag-error", "").replace("&", "§");
        tagPermission   = config.getString("messages.tag-permission", "").replace("&", "§");
        tagSet          = config.getString("messages.tag-set", "").replace("&", "§");
    }
}
