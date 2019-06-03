package tribe;

/**
 *
 * @author conor
 */
public class TileCollision {
    private Tile collider;
    private Tile collidee;
    
    public TileCollision(Tile collider, Tile collidee) {
        this.collider = collider;
        this.collidee = collidee;
    }
    
    // manages interaction between two tiles
    public boolean collision() {
        
        // if both tiles are passable
        if(collider.getAcre().getPassable() && collidee.getAcre().getPassable()) {
            
            // if both have a member
            if(collider.getHasMember() && collidee.getHasMember()) {
                return false;

            // if only collider has a member
            } else if(collider.getHasMember() && !collidee.getHasMember()) {
                return true;

            // if only collidee has a member
            } else if(!collider.getHasMember() && collidee.getHasMember()) {
                return false;

            // if neither have a member
            } else {
                return false;
            }
            
        // if either have are impassable
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
