package com.sr.KT_ecommerce_spring_jwt_backend.Repository;

import com.sr.KT_ecommerce_spring_jwt_backend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    boolean existsByEmail(String email);

}
