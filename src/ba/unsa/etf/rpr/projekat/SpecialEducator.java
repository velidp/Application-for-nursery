package ba.unsa.etf.rpr.projekat;

public class SpecialEducator extends Educator {
    Institution institution = new Institution();

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
