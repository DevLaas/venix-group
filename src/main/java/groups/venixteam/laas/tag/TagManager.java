package groups.venixteam.laas.tag;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import java.util.*;

public class TagManager {

    private final Map<String, Tag> tags = new HashMap<>();

    public TagManager(ConfigurationSection section) {
        for (String key : section.getKeys(false)) {
            String name = section.getString(key + ".name");
            String prefix = section.getString(key + ".prefix");
            String perm = section.getString(key + ".perm");
            int priority = section.getInt(key + ".priority");
            tags.put(key.toLowerCase(), new Tag(key, name, prefix, perm, priority));
        }
    }

    public List<Tag> getTags(Player player) {
        List<Tag> tagList = new ArrayList<>();

        for (Tag tag : tags.values()) {
            if (tag.getPermission() == null || tag.getPermission().isEmpty() || player.hasPermission(tag.getPermission())) {
                tagList.add(tag);
            }
        }

        tagList.sort(Comparator.comparingInt(Tag::getPriority));
        return tagList;
    }

    public Tag getTagById(String id) {
        return tags.get(id.toLowerCase());
    }

    public Tag getPlayerTag(Player player) {
        List<Tag> tags = getTags(player);
        return tags.isEmpty() ? null : tags.get(0);
    }

    public Tag getNormalTag() {
        return tags.values().stream()
                .filter(t -> t.getPermission() == null || t.getPermission().isEmpty())
                .min(Comparator.comparingInt(Tag::getPriority))
                .orElse(null);
    }
}
