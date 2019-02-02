package ba.unsa.etf.rpr.projekat;

public class Educator extends Person{
    Institution institution = new Institution();
    boolean zaDjecuSaPosebnimPotrebama;

    public boolean isZaDjecuSaPosebnimPotrebama() {
        return zaDjecuSaPosebnimPotrebama;
    }

    public void setZaDjecuSaPosebnimPotrebama(boolean zaDjecuSaPosebnimPotrebama) {
        this.zaDjecuSaPosebnimPotrebama = zaDjecuSaPosebnimPotrebama;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
