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
                return memberMovement();
            } else {
                return memberPlacement();
            }
        } else {
            System.out.println("No member wrong method call");
            return false;
        }
    }
    
    private boolean memberMovement() {
        if(collidee.getAcre().getPassable()) {
            if(!collidee.getHasMember()) {
                if(collidee.getAcre().getFood() >= 0) {
                    return true;
                } else {
                    collider.getMember().memberDeath();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private boolean memberPlacement() {
        if(collidee.getAcre().getPassable()) {
            if(!collidee.getHasMember()) {
                return true;
            } else {
                return false;
            }
        } else {
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
