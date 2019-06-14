package tribe;

/**
 *
 * @author conor
 */
public class TileCollisionManager {
    private Tile collider;
    private Tile collidee;
    
    public TileCollisionManager(Tile collider, Tile collidee) {
        this.collider = collider;
        this.collidee = collidee;
    }
    
    // collision where collider has a member
    public boolean memberToTileCollide() {
        if(collider.getHasMember()) {
            if(collider.getHasAcre()) {
                memberMovement();
            } else {
                return memberPlacement();
            }
        } else {
            System.out.println("No member wrong method call");
        }
        return false;
    }
    
    //  collider has acre & member
    private void memberMovement() {
        if(collidee.getAcre().getPassable()) {
            
            // collidee not has member
            if(!collidee.getHasMember()) {
                if(collidee.getAcre().getFood() > 0) {
                    collider.getMember().changeSatiation(0.1);
                    collidee.getAcre().changeFood(-0.1);
                    collider.getMember().setCords(collidee.getAcre().getCords());
                } else {
                    collider.getMember().changeSatiation(-0.1);
                }
            
            // collidee has member
            } else {
                if(collider.getAcre().getFood() > 0) {
                    collider.getMember().changeSatiation(0.1);
                    collider.getAcre().changeFood(-0.1);
                } else {
                    if(collider.getMember().getSatiation() > 0) {
                        collider.getMember().changeSatiation(-0.1);
                    } else {
                        collider.getMember().memberDeath();
                    }
                }
            }
        } else {
            // nothing
        }
    }
    
    // collider has member
    private boolean memberPlacement() {
        if(collidee.getAcre().getPassable()) {
            if(!collidee.getHasMember()) {
                return true;
            } else {
                // nothing
                return false;
            }
        } else {
            // nothing
            return false;
        }
    }
    
    // GETTERS && SETTERS
    // sets collider
    public void setCollider(Tile a) {
        collider = a;
    }
    
    // returns collider
    public Tile getCollider() {
        return collider;
    }
    
    // sets collidee
    public void setCollidee(Tile b) {
        collidee = b;
    }
    
    // returns collidee
    public Tile getCollidee() {
        return collidee;
    }
}
