package tribe;

import java.util.ArrayList;

/**
 *
 * @author conor
 */
public class Civilization {
    private ArrayList<Nation> nations;
    private int popNation;
    private int popCiv;
    
    public Civilization() {
        this.nations = new ArrayList();
        this.popNation = nations.size();
        this.popCiv = 0;
    }
    
    public Civilization(int popNation, int popMember) {
        this.nations = new ArrayList();
        this.popNation = nations.size();
        this.popCiv = 0;
    }
    
    public void addNation(Nation newNation) {
        nations.add(newNation);
        setPopNationLarger();
    }
    
    // moves all nations
    public void randMoveAllNation(Map land) {
        for(Nation n : nations) {
            n.randMoveAllMember(land);
        }
    }
    
    // SETTERS && GETTERS
    // set nation list
    public void setNationList(ArrayList<Nation> nations) {
        this.nations = nations;
    }
    
    // returns nation list
    public ArrayList<Nation> getNationList() {
        return nations;
    }
    
    // popNation++ && popNation--
    public void setPopNationLarger() {
        popNation++;
    }
    public void setPopNationSmaller() {
        popNation--;
    }
    
    // returns popNation
    public int getPopNation() {
        popNation = nations.size();
        return popNation;
    }
    
    // returns popWorld
    public int getPopCiv() {
        int tempPop = 0;
        for(Nation n : nations) {
            tempPop += n.getNationPop();
        }
        popCiv = tempPop;
        return popCiv;
    }
}
