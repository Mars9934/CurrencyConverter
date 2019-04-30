package Model;

public class Converter {
    private String val;


    public Converter(String val) {
        this.val = val;
    }

    public Double convVal(String r) {
        Double dVal = Double.parseDouble(val);
        Double rate = Double.parseDouble(r);
        return dVal * rate;
    }

}
