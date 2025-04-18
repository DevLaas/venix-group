package groups.venixteam.laas.listeners;

import groups.venixteam.laas.Language;
import groups.venixteam.laas.Main;
import groups.venixteam.laas.database.ProfileTable;
import groups.venixteam.laas.tag.Tag;
import groups.venixteam.laas.tag.TagManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.SQLException;

public class PlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent evt) {
        ProfileTable table = Main.getProfileTable();
        TagManager tagManager = Main.getTagManager();
        Tag tag;

        try {
            tag = table.getTag(evt.getPlayer().getUniqueId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
            tag = null;
        }

        if (tag == null || (!tag.getPermission().isEmpty() && !evt.getPlayer().hasPermission(tag.getPermission()))) {
            tag = tagManager.getNormalTag();
        }

        String prefix = tag != null ? tag.getPrefix() : "";

        if (Language.tagChatEnable) {
            String chatFormat = Language.tagChatFormat
                    .replace("{TAG}", prefix)
                    .replace("{PLAYER}", evt.getPlayer().getName())
                    .replace("{MSG}", evt.getMessage())
                    .replace("&", "§");
            evt.setFormat(chatFormat);
        }
    }
}
