package dev.redbeans.bootcamp202506eirzarog.springapi.repositories;

import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM public.users WHERE username = :username")
    public User findUserByUsername(@Param("username") String username);


}
