package edu.sabanciuniv.nanuvcell.repository;

import edu.sabanciuniv.nanuvcell.model.RemainingUse;
import edu.sabanciuniv.nanuvcell.model.RemainingUseHomeInternet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RemainingUseHomeInternetRepository extends JpaRepository<RemainingUseHomeInternet, Long> {

    @Query("select h from RemainingUseHomeInternet h inner join User u on h.user.id = u.id where u.id = :userId")
    Optional<RemainingUseHomeInternet> findRemainingUseByUserId(@Param("userId") Long id);
}
