package tribe;

import java.util.ArrayList;

/**
 *
 * @author conor
 */
public class TNode<E> {
    private E data;
    private ArrayList<TNode> children;
    private TNode parent;
    
    public TNode() {
        this.data = null;
        this.children = null;
    }
    
    public TNode(E newData) {
        this.data = newData;
        this.children = null;
    }
    
    public E getData() {
        return data;
    }
    public void setData(E newData) {
        data = newData;
    }
    public ArrayList<TNode> getChildren() {
        return children;
    }
    public void setChildren(ArrayList<TNode> newLeaf) {
        children = newLeaf;
    }
    public TNode getParent() {
        return parent;
    }
    public void setParent(TNode newParent) {
        parent = newParent;
    }
}
