package com.sp.spingboot_restfull.repository;

import com.sp.spingboot_restfull.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
