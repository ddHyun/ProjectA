package org.tourGo.models.community.review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<User_test, Integer>{

	User_test save(User_test user);
}
