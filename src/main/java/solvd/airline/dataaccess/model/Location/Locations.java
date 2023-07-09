package solvd.airline.dataaccess.model.Location;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Locations {

    @XmlElement(name = "location")
    @JsonProperty("locations")
    private List<Location> dataList;

    public Locations() {
        dataList = new ArrayList<>();
    }

    public List<Location> getDataList() {
        return dataList;
    }

    public void setDataList(List<Location> dataList) {
        this.dataList = dataList;
    }
}
