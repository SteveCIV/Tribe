package tribe;

/**
 *
 * @author conor
 */
public class Tile {
    private boolean hasMember;
    private Member m;
    private boolean hasArce;
    private Acre a;
    
    public Tile() {
        this.hasMember = false;
        this.m = null;
        this.hasArce = false;
        this.a = null;
    }
    
    public Tile(Member member, Acre arce) {
        this.hasMember = true;
        this.m = member;
        this.hasArce = true;
        this.a = arce;
    }
    
    // GETTERS && SETTERS
    public void setHasMember(boolean b) {
        hasMember = b;
    }
    
    public boolean getHasMember() {
        return hasMember;
    }
    
    public void setMember(Member member) {
        m = member;
    }
    
    public Member getMember() {
        return m;
    }
    
    public void setHasAcre(boolean b) {
        hasArce = b;
    }
    
    public boolean getHasAcre() {
        return hasArce;
    }
    
    public void setAcre(Acre arce) {
        a = arce;
    }
    
    public Acre getAcre() {
        return a;
    }
}
