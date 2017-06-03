package cs143.business;

import cs143.dataaccess.SsnMap;
import cs143.domain.Retiree;

public class RetireeManager {
    
    private SsnMap map;
    
    public RetireeManager() {
        map = new SsnMap();
    }

    public boolean add(Retiree retiree) {
        return map.insert(retiree.getSsn(), retiree);
    }
    
    public boolean delete(long ssn) {
        return map.remove(ssn);
    }
    
    public Retiree get(long ssn) {
        return map.get(ssn);
    }
    
}
