package main.embeddables;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author cvoinea
 */
@Embeddable
public class BuildingPK implements Serializable {

    private int number;
    private String code;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
