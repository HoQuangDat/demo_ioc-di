package guild.api.security.controller;

public class XeMay {
    private IDongCo iDongCo;
    //constructor
    public XeMay(IDongCo iDongCo) {
        this.iDongCo = iDongCo;
    }
    //setter
    public void setIDongCo(IDongCo iDongCo) {
        this.iDongCo = iDongCo;
    }
    //getter
    public IDongCo getIDongCo() {
        return iDongCo;
    }
}
