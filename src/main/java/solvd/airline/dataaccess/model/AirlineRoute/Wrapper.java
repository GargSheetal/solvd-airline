package solvd.airline.dataaccess.model.AirlineRoute;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "airline_routes")
public class Wrapper {

    @XmlElement(name = "airline_route")
    private List<AirLineRoute> dataList;

    public Wrapper() {
        // JAXB needs this
    }

    public List<AirLineRoute> getDataList() {
        return dataList;
    }

    public void setDataList(List<AirLineRoute> dataList) {
        this.dataList = dataList;
    }
}
