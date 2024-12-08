package packagetracking.server.pkg;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import packagetracking.server.Courier.Courier;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = true)
    private Courier courier;

    private String deliveryAddress;

    @Column(nullable = false)
    private boolean payOnDelivery;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @ApiModelProperty(hidden = true)
    private Date createdOn;

    @Enumerated(EnumType.STRING)
    private PackageStatus status;
}

