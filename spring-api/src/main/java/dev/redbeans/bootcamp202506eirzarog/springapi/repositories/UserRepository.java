package dev.redbeans.bootcamp202506eirzarog.springapi.repositories;

import dev.redbeans.bootcamp202506eirzarog.springapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM public.users WHERE username = :username")
    User findUserByUsername(@Param("username") String username);

    // jpa queries with parameters
//    User findUserByEmail(String email);
    // or
//    User findUserByUsername(String username);
    //  or
//    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


}
