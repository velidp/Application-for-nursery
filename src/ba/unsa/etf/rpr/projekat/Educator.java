package ba.unsa.etf.rpr.projekat;

public class Educator extends Person{
    Institution institution = new Institution();

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
