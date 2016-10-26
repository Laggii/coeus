package model;

import java.util.Objects;

/**
 * Created by Alexeev on 29.09.2016.
 */

/**
 * Role Bean with Builder pattern
 */
public class Role {

    private long roleId;

    private String name;
    private String description;

    public long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    private Role(Builder builder) {
        roleId = builder.roleId;
        name = builder.name;
        description = builder.description;
    }

    public static class Builder {
        private long roleId;

        private String name;
        private String description;

        public Builder setRoleId(long roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Role build() {
            if (name == null || description == null) {
                throw new IllegalArgumentException("Role required parameters are empty");
            }
            return new Role(this);
        }
    }
}
