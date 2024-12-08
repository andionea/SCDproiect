package packagetracking.server.Courier;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import packagetracking.server.pkg.Package;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Optional<Courier> getCourierById(int id) {
        return courierRepository.findById(id);
    }

    public Courier saveCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    public void deleteCourier(int id) {
        courierRepository.deleteById(id);
    }

    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierRepository.getAllCouriersWithoutPendingPackages();
    }

    public List<Object[]> getAllManagersAndDeliveredNumber() {
        return courierRepository.getAllManagersAndDeliveredNumber();
    }

    public List<Package> getPackagesForCourier(int courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Courier not found"));
        return courier.getPackages();
    }
}
