package groups.venixteam.laas.cmd;

import groups.venixteam.laas.Language;
import groups.venixteam.laas.Main;
import groups.venixteam.laas.tag.NameTagAPI;
import groups.venixteam.laas.tag.Tag;
import groups.venixteam.laas.tag.TagManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.List;

public class TagCommand implements CommandExecutor {

    private final TagManager tagManager;

    public TagCommand(TagManager tagManager) {
        this.tagManager = tagManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem usar esse comando.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            List<Tag> tags = tagManager.getTags(player);

            if (tags.isEmpty()) {
                player.sendMessage(Language.tagAccess);
                return true;
            }

            player.sendMessage(Language.tagAvailable + "\n");
            for (Tag tag : tags) {
                player.sendMessage(" §f• " + tag.getName().replace("&","§"));
            }
            player.sendMessage("\n§6§lTAG: §fUtilize: §f/tag <tag>");
            return true;
        }

        String tagId = args[0].toLowerCase();
        Tag tag = tagManager.getTagById(tagId);

        if (tag == null) {
            player.sendMessage(Language.tagError);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f, 2.0f);
            return true;
        }

        if (!tag.getPermission().isEmpty() && !player.hasPermission(tag.getPermission())) {
            player.sendMessage(Language.tagPermission);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f, 2.0f);
            return true;
        }

        NameTagAPI.setTag(player, tag.getPrefix(), player);
        try {
            Main.getProfileTable().savePlayer(player.getUniqueId().toString(), tag);
        } catch (SQLException e) {
            player.sendMessage("§cErro ao salvar sua tag no banco de dados.");
            e.printStackTrace();
        }

        player.sendMessage(Language.tagSet.replace("{TAG}", tag.getName()).replace("&","§"));
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 2f);
        return true;
    }
}
