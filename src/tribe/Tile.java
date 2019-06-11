package tribe;

/**
 *
 * @author conor
 */
public class Tile {
    private Member m;
    private Acre a;
    
    public Tile() {
        this.m = null;
        this.a = null;
    }
    
    public Tile(Member member) {
        this.m = member;
        this.a = null;
    }
    
    public Tile(Acre acre) {
        this.m = null;
        this.a = acre;
    }
    
    public Tile(Member member, Acre acre) {
        this.m = member;
        this.a = acre;
    }
    
    // GETTERS && SETTERS
    public void setMember(Member member) {
        m = member;
    }
    
    public Member getMember() {
        return m;
    }
    
    public boolean getHasMember() {
        return (m != null);
    }
    
    public void setAcre(Acre acre) {
        a = acre;
    }
    
    public Acre getAcre() {
        return a;
    }
    
    public boolean getHasAcre() {
        return (a != null);
    }
}
