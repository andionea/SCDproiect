package packagetracking.server.Courier;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    private Courier manager;

    @OneToMany(mappedBy = "manager")
    @JsonManagedReference
    private List<Courier> subordinates;
    
    @OneToMany(mappedBy = "courier")
    private List<packagetracking.server.pkg.Package> packages;
    

}
