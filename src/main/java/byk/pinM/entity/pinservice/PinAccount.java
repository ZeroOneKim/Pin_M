package byk.pinM.entity.pinservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PinAccount {
    @Id
    @Column(unique = true)
    private String accountnum;
}
