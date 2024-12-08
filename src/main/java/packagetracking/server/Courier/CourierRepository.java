package packagetracking.server.Courier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Integer> {

    @Query("SELECT c FROM Courier c WHERE NOT EXISTS (SELECT p FROM Package p WHERE p.courier = c AND p.status = 'PENDING')")
    List<Courier> getAllCouriersWithoutPendingPackages();

    @Query("SELECT c.manager AS manager, COUNT(p) AS deliveredCount " +
           "FROM Courier c LEFT JOIN Package p ON p.courier = c " +
           "WHERE p.status = 'DELIVERED' GROUP BY c.manager")
    List<Object[]> getAllManagersAndDeliveredNumber();
}
