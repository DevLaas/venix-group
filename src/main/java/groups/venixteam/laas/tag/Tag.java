package groups.venixteam.laas.tag;

public class Tag {

    private final String id;
    private final String name;
    private final String prefix;
    private final String permission;
    private final int priority;

    public Tag(String id, String name, String prefix, String permission, int priority) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.permission = permission;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPermission() {
        return permission;
    }

    public int getPriority() {
        return priority;
    }
}
