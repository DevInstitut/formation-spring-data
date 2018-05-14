# Vous vous souvenez de JDBC ?

```java
try {
    conn = this.datasource.getConnection();
    stmt = conn.prepareStatement("select ID, FNAME, LNAME from person where LNAME = ?");
    stmt.setString(1, lname);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        p = new Person();
        p.setId(rs.getInt("ID"));
        p.setFirstName(rs.getString("FNAME"))
        p.setLastName(rs.getString("LNAME"));
    }
} catch (SQLException e) {
    LOGGER.error(e);
} finally {
    try {
        if (stmt != null)
            stmt.close();
    } catch (SQLException e) {
        LOGGER.warn(e);
    }
    try {
        if (conn != null)
            conn.close();
    } catch (SQLException e) {
        LOGGER.warn(e);
    }
}
```
