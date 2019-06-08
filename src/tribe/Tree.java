package tribe;

/**
 *
 * @author conor
 */
public class Tree<E> {
    private TNode base;
    private int size = 0;
    
    public Tree() {
        base = null;
        size = 0;
    }
    
    public Tree(TNode ca) {
        this.base = ca;
        this.size++;
    }
    
    public void setBase(TNode newAncestor) {
        base = newAncestor;
    }
    public TNode getBase() {
        return base;
    }
    public int getSize() {
        return size;
    }
}
