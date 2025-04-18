package groups.venixteam.laas.cmd;

import groups.venixteam.laas.Language;
import groups.venixteam.laas.Main;
import groups.venixteam.laas.api.TitleAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class GroupCommand implements CommandExecutor {

    private static List<String> VIPS = Main.getInstance().getInstance().getConfig().getStringList("vips");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSomente jogadores podem utilizar esse comando.");
            return true;
        }

        Player player = (Player) sender;
        LuckPerms luckPerms = LuckPermsProvider.get();

        if (!player.hasPermission("venixgroup.group")) {
            player.sendMessage(Language.permissionError);
            return true;
        }

        if (args.length < 2 || args.length > 3) {
            player.sendMessage("§c§lERRO: §cUtilize: §f/group <adicionar/remover> <player> <group>");
            return true;
        }

        String action = args[0];
        String targetName = args[1];
        String group = args.length == 3 ? args[2].toLowerCase() : null;
        Set<Group> groups = luckPerms.getGroupManager().getLoadedGroups();

        if (group == null || groups.stream().noneMatch(g -> g.getName().equalsIgnoreCase(group))) {
            String listGroups = groups.stream().map(Group::getName).collect(Collectors.joining(", "));
            player.sendMessage("§6§lGROUP: §cGrupos disponíveis: §e\n" + listGroups);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f,2.0f);
            return true;
        }

        if (!sender.isOp() && targetName.equals(sender.getName())) {
            player.sendMessage(Language.playerGroupError);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f,2.0f);
            return true;
        }

        Player target = Bukkit.getServer().getPlayerExact(targetName);
        if (target == null) {
            player.sendMessage(Language.playerError);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f,2.0f);
            return true;
        }

        User user = luckPerms.getUserManager().getUser(target.getUniqueId());
        if (user == null) {
            player.sendMessage(Language.playerInfoError);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f,2.0f);
            return true;
        }

        switch (action.toLowerCase()) {
            case "adicionar":
                if (hasGroup(user, group)) {
                    player.sendMessage(Language.playerGroupHas.replace("{GROUP}", group.toUpperCase()));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.5f, 2.0f);
                    return true;
                }

                if (VIPS.contains(group) && Language.enableVipTitleActive == true) {
                    TitleAPI titleAPI = new TitleAPI();
                    titleAPI.send(player, Language.vipTitleFadein, Language.vipTitleShowtime, Language.vipTitleFadeout,
                            Language.vipTitleHeader.replace("{PLAYER}", target.getName()).replace("{GROUP}", group.toUpperCase()),
                            Language.vipTitleFooter.replace("{PLAYER}", target.getName()).replace("{GROUP}", group.toUpperCase()));

                    vipSet(target.getLocation(), 1, 5);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 0.5f, 5.0f);
                    }

                    user.data().add(InheritanceNode.builder(group).build());
                    user.setPrimaryGroup(group);
                    player.sendMessage(Language.playerGroupSet.replace("{PLAYER}", target.getName()).replace("{GROUP}", group.toUpperCase()));
                    player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 0.5f,2.0f);
                } else {
                    user.data().add(InheritanceNode.builder(group).build());
                    player.sendMessage(Language.playerGroupSet.replace("{PLAYER}", target.getName()).replace("{GROUP}", group.toUpperCase()));
                    player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 0.5f,2.0f);
                }
                break;
            case "remover":
                user.data().remove(InheritanceNode.builder(group).build());
                player.sendMessage(Language.playerGroupRemove.replace("{PLAYER}", target.getName()).replace("{GROUP}", group.toUpperCase()));
                player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 0.5f,2.0f);
                break;
            default:
                player.sendMessage("§c§lERRO: §cUtilize: §f/group <adicionar/remover> <player> <group>");
                return true;
        }
        luckPerms.getUserManager().saveUser(user);
        return true;
    }

    public void vipSet(final Location location, final int count, int power) {
        Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder()
                .with(FireworkEffect.Type.BALL)
                .withColor(Color.RED, Color.GREEN, Color.BLUE)
                .withFade(Color.YELLOW)
                .build();
        meta.addEffect(effect);
        meta.setPower(power);
        firework.setFireworkMeta(meta);

        new BukkitRunnable() {
            @Override
            public void run() {
                firework.detonate();
            }
        }.runTaskLater(Main.getInstance(), 30L);
    }

    private boolean hasGroup(User user, String groupName) {
        QueryOptions queryOptions = user.getQueryOptions();
        return user.getInheritedGroups(queryOptions).stream()
                .anyMatch(group -> group.getName().equalsIgnoreCase(groupName));
    }
}
