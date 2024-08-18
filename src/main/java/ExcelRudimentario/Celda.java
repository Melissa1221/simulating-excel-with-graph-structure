package ExcelRudimentario;

public class Celda implements Comparable<Celda>{
    private String id;
    private String content;
    private Double value;
    
    public Celda(String id,String content){
        this.id = id;
        this.content = content;
        this.value = Double.NaN;
    }
    
    public Celda(String id){
        this(id,"");
    }

    public String getId() {
        return id;
    }
    
    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public boolean isNumeric() {
        try {
            Double.valueOf(content);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @Override
    public int compareTo(Celda o) {
        if(this.value < o.getValue()){
            return -1;
        }else if(this.value > o.getValue()){
            return 1;
        }else{
            return 0;
        }
    }
}
