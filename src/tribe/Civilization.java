package tribe;

import java.util.ArrayList;

/**
 *
 * @author conor
 */
public class Civilization {
    private ArrayList<Nation> nations;
    private int popNation;
    private int popMember;
    
    public Civilization() {
        this.nations = new ArrayList();
        this.popNation = nations.size();
        this.popMember = 0;
    }
    
    public Civilization(int popNation, int popMember) {
        this.nations = new ArrayList();
        this.popNation = nations.size();
        this.popMember = 0;
    }
    
    public void addNation(Nation newNation) {
        //Nation newNation = new Nation(n);
        nations.add(newNation);
        setPopNationLarger();
    }
    
    public void randMoveAllNation(Map land) {
        for(Nation n : nations) {
            n.randMoveAllMember(land);
        }
    }
    
    // SETTERS && GETTERS
    // set civilization
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
    
    // returns worldPop
    public int getPopMember() {
        int worldPop = 0;
        for(Nation n : nations) {
            worldPop += n.getNationPop();
        }
        popMember = worldPop;
        return popMember;
    }
}
