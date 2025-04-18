package groups.venixteam.laas.tag;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameTagAPI {

    public static void setTag(Player player, String tag, Player toSetFor) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam(player.getName());
        if (team == null) {
            team = board.registerNewTeam(player.getName());
        }

        team.setPrefix(tag.replace("&", "§"));
        team.addPlayer(player);
        Iterator var6 = Bukkit.getOnlinePlayers().iterator();

        while(var6.hasNext()) {
            Player players = (Player)var6.next();
            players.setScoreboard(board);
        }

    }
}
