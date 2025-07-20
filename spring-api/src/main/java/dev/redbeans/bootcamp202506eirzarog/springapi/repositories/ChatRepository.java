package dev.redbeans.bootcamp202506eirzarog.springapi.repositories;

import dev.redbeans.bootcamp202506eirzarog.springapi.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    /**
     * Finds chats by username.
     * If username is null, returns all chats.
     *
     * @param username the username to filter chats by
     * @return a list of chats associated with the given username
     */
    // with JPA:
    @Query("SELECT c FROM Chat c " +
            "LEFT JOIN User u ON c.userId = u.id " +
            "WHERE (:username IS NULL OR u.username = :username) ")
    List<Chat> findChatsByUsername(@Param("username") String username);

    /**
     * Finds all chats based on the provided criteria.
     * If any parameter is null, it will not filter by that parameter.
     *
     * @param userId the ID of the user to filter chats by (optional)
     * @param username the username to filter chats by (optional)
     * @param from the start date to filter chats (optional)
     * @param to the end date to filter chats (optional)
     * @return a list of chats matching the criteria
     */
    @Query(nativeQuery = true,
            value = "SELECT c.* " +
                    "FROM public.chats c " +
                    "   inner join public.users u on c.user_id = u.id " +
                    "WHERE (:userId is null or user_id = :userId) AND " +
                    "   (:username is null or u.username = :username) AND " +
                    "   (CAST(:from AS timestamp) is null or created_at >= :from) AND " +
                    "   (CAST(:to AS timestamp) is null or created_at <= :to) ")
    List<Chat> findAll(Long userId,
                       String username,
                       Instant from,
                       Instant to);


//    Same as above with JPA: JPQL query, explicit join for left and right joins as needed
    @Query("SELECT c FROM Chat c " +
            "  JOIN User u " +
            "WHERE (:userId is null or c.userId = :userId) AND " +
            "  (:username is null or u.username = :username) AND " +
            "  (CAST(:from AS timestamp)  is null or c.createdAt >= :from) AND " +
            "  (CAST(:to AS timestamp) is null or c.createdAt <= :to)")
    List<Chat> findAllWithJPA(@Param("userId") Long userId,
                              @Param("username") String username,
                              @Param("from") Instant from,
                              @Param("to") Instant to);

}
