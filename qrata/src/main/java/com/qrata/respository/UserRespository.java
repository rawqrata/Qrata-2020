package com.qrata.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qrata.models.User;

public interface UserRespository extends JpaRepository<User,Long>{
    
	@Query("select u from User u where u.userName = :userName")
	public abstract User findByUserName(@Param("userName") String userName);
	
	/*
	 * SELECT * FROM user u INNER JOIN userinfo uf ON u.id = uf.user_id INNER JOIN
	 * user_roles r ON u.id = r.users_id WHERE u.status = 1 AND r.roles_id = 3 AND
	 * (uf.firstname like "%A%" or uf.lastname like "%A%" or uf.email like "%A%" or
	 * u.username like "%A%");
	 */ 

	/*
	 * @Query("SELECT u FROM User INNER JOIN u.userinfo uf  INNER JOIN u.roles r WHERE u.status = :status"
	 * ) public abstract List<User> getExpertList(@Param("status") Short staus);
	 */

}
