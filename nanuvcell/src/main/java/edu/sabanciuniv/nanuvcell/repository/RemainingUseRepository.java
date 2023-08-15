package edu.sabanciuniv.nanuvcell.repository;

import edu.sabanciuniv.nanuvcell.model.RemainingUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RemainingUseRepository extends JpaRepository<RemainingUse, Long> {

    @Query("select r from RemainingUse r inner join User u on r.user.id = u.id where u.id = :userId")
    Optional<RemainingUse> findRemainingUseByUserId(@Param("userId") Long id);
}
