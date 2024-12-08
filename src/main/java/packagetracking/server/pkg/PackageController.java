package packagetracking.server.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/package")
@CrossOrigin
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping
    public Package create(@RequestBody Package myPackage) {
        return packageService.create(myPackage);
    }

    @GetMapping
    public List<Package> getAllPackages() {
        return packageService.findAllPackages();
    }

    @GetMapping("/courier/{id}")
    public List<Package> getPackagesForCourier(@PathVariable int id) {
        return packageService.findPackagesForCourier(id);
    }

    @GetMapping("/unassigned")
    public List<Package> getUnassignedPackages() {
        return packageService.findUnassignedPackages();
    }

    @PutMapping("/{id}/status")
    public Package updateStatus(@PathVariable int id, @RequestParam PackageStatus status) {
        return packageService.updateStatus(id, status);
    }
    
}

