package groups.venixteam.laas.listeners;

import groups.venixteam.laas.Language;
import groups.venixteam.laas.Main;
import groups.venixteam.laas.database.ProfileTable;
import groups.venixteam.laas.tag.NameTagAPI;
import groups.venixteam.laas.tag.Tag;
import groups.venixteam.laas.tag.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent evt) {
        evt.setJoinMessage(null);
        Player player = evt.getPlayer();
        ProfileTable table = Main.getProfileTable();
        TagManager tagManager = Main.getTagManager();

        try {
            Tag saved = table.getTag(player.getUniqueId().toString());
            Tag defaultTag = tagManager.getNormalTag();
            Tag tag;

            if (saved != null && (saved.getPermission().isEmpty() || player.hasPermission(saved.getPermission()))) {
                tag = saved;
            } else {
                tag = defaultTag;
            }

            NameTagAPI.setTag(player, tag.getPrefix(), player);
            if (Language.tagLobbyEnabled && !tag.getId().equals(defaultTag.getId())) {
                Bukkit.broadcastMessage(Language.tagLobbyFormat
                                .replace("{TAG}", tag.getPrefix())
                                .replace("{PLAYER}", player.getName())
                                .replace("&", "§"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
