package groups.venixteam.laas;

public class Language {

    public static boolean enable$viptitle$active = Main.getInstance().getConfig().getBoolean("title.active");
    public static String viptitle$header = Main.getInstance().getConfig().getString("title.header").replace("&", "§");
    public static String viptitle$footer = Main.getInstance().getConfig().getString("title.footer").replace("&", "§");
    public static int $viptitle$fadein = Main.getInstance().getConfig().getInt("title.fadein");
    public static int $viptitle$showtime = Main.getInstance().getConfig().getInt("title.showtime");
    public static int $viptitle$fadeout = Main.getInstance().getConfig().getInt("title.fadeout");

    public static String permission$error = Main.getInstance().getConfig().getString("messages.permission").replace("&", "§");
    public static String player$error = Main.getInstance().getConfig().getString("messages.player-error").replace("&", "§");
    public static String playerinfo$error = Main.getInstance().getConfig().getString("messages.playerinfo-error").replace("&", "§");
    public static String playergroup$error = Main.getInstance().getConfig().getString("messages.playergroup-error").replace("&", "§");
    public static String playergroup$has = Main.getInstance().getConfig().getString("messages.playergroup-has").replace("&", "§");

    public static String playergroup$set = Main.getInstance().getConfig().getString("messages.playergroup-set").replace("&", "§");
    public static String playergroup$remove = Main.getInstance().getConfig().getString("messages.playergroup-remove").replace("&", "§");

}
