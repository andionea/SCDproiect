package packagetracking.server.Courier;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Courier manager;

    /* 
    @OneToMany(mappedBy = "manager")
    private List<Courier> subordinates;
    */

    @OneToMany(mappedBy = "courier")
    private List<packagetracking.server.pkg.Package> packages;

}
