package packagetracking.server.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Transactional
    public Package create(Package myPackage) {
        return packageRepository.save(myPackage);
    }

    public List<Package> findAllPackages() {
        return packageRepository.findAll();
    }

    public List<Package> findUnassignedPackages() {
        return packageRepository.findByCourierIsNull();
    }

    public List<Package> findPackagesForCourier(int courierId) {
        return packageRepository.findByCourierId(courierId);
    }

    @Transactional
    public Package updateStatus(int packageId, PackageStatus status) {
        Package myPackage = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        
        if (status == PackageStatus.DELIVERED && myPackage.getCourier() == null) {
            throw new IllegalArgumentException("Package cannot be delivered without a courier");
        }
        myPackage.setStatus(status);
        return packageRepository.save(myPackage);
    }
}
