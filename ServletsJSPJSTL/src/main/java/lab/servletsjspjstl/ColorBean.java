package lab.servletsjspjstl;

public class ColorBean {
    private String foregroundColor;
    private String backgroundColor;
    private String frame;


    public String getFrame(){
        return frame;
    }
    public void setFrame(String frame){
        if(frame.equals("yes")){
            this.frame = "1";
        }
        else{
            this.frame ="0";
        }
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
