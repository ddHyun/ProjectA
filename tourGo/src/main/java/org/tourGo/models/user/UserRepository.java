package org.tourGo.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.tourGo.models.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserId(String userId);
	
}
