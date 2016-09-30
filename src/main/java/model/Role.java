package model;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class Role {

    private final int roleId;

    private final String name;
    private final String description;

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private Role(RoleBuilder builder) {
        roleId = builder.roleId;
        name = builder.name;
        description = builder.description;
    }

    public static class RoleBuilder {
        private int roleId;

        private String name;
        private String description;

        public RoleBuilder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public RoleBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
