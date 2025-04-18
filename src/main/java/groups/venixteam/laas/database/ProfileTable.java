package groups.venixteam.laas.database;

import groups.venixteam.laas.tag.Tag;
import groups.venixteam.laas.tag.TagManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileTable {

    private final Connection connection;
    private final TagManager tagManager;

    public ProfileTable(Connection connection, TagManager tagManager) {
        this.connection = connection;
        this.tagManager = tagManager;
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS venix_group (" +
                "uuid VARCHAR(36) PRIMARY KEY, " +
                "tag_id VARCHAR(50))";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
        }
    }

    public void savePlayer(String uuid, Tag tag) throws SQLException {
        String sql = "INSERT INTO venix_group (uuid, tag_id) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE tag_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uuid);
            stmt.setString(2, tag != null ? tag.getId() : null);
            stmt.setString(3, tag != null ? tag.getId() : null);
            stmt.executeUpdate();
        }
    }

    public Tag getTag(String uuid) throws SQLException {
        String sql = "SELECT tag_id FROM venix_group WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uuid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tagId = rs.getString("tag_id");
                    return tagManager.getTagById(tagId);
                }
            }
        }
        return null;
    }

    public void deletePlayer(String uuid) throws SQLException {
        String sql = "DELETE FROM venix_group WHERE uuid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uuid);
            stmt.executeUpdate();
        }
    }
}