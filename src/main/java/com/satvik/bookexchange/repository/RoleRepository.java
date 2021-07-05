package com.satvik.bookexchange.repository;

import com.satvik.bookexchange.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "Select id from ROLE where role_name=:role_name", nativeQuery = true)
    Integer findIdForRole(@Param("role_name") String role);

    Role findByRoleName(String roleName);
}
