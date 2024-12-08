package packagetracking.server.Courier;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/couriers")
@CrossOrigin
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    // Get all couriers
    @GetMapping
    public List<Courier> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    // Get a courier by ID
    @GetMapping("/{id}")
    public Optional<Courier> getCourierById(@PathVariable int id) {
        return courierService.getCourierById(id);
    }

    // Add a new courier
    @PostMapping
    public Courier addCourier(@RequestBody Courier courier) {
        return courierService.saveCourier(courier);
    }

    // Update an existing courier
    @PutMapping("/{id}")
    public Courier updateCourier(@PathVariable int id, @RequestBody Courier courier) {
        courier.setId(id);
        return courierService.saveCourier(courier);
    }

    // Delete a courier
    @DeleteMapping("/{id}")
    public void deleteCourier(@PathVariable int id) {
        courierService.deleteCourier(id);
    }

    // Get packages for a specific courier
    @GetMapping("/{id}/packages")
    public List<?> getPackagesForCourier(@PathVariable int id) {
        return courierService.getPackagesForCourier(id);
    }

    // Get all couriers without pending packages
    @GetMapping("/without-pending")
    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierService.getAllCouriersWithoutPendingPackages();
    }

    // Get all managers and their delivered package count
    @GetMapping("/managers-delivered")
    public List<Object[]> getAllManagersAndDeliveredNumber() {
        return courierService.getAllManagersAndDeliveredNumber();
    }
}
