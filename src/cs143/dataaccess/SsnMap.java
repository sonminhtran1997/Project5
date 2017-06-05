package cs143.dataaccess;

import cs143.domain.Retiree;

public class SsnMap {

    private static int CAPACITY = 1024;
    private IDataAccess data = new DataAccessFileImpl();

    public Retiree get(long ssn) {
        //TODO - write this code
        int fileIndex = this.hash(ssn);
        SsnAvl treeResult;
        treeResult = data.retrieveAvl(this.hash(ssn));
        if (treeResult == null) {
            return null;
        }
        return treeResult.get(ssn);
    }

    public boolean insert(long ssn, Retiree r) {
        SsnAvl treeFound;
        boolean success;
        int index = this.hash(ssn);
        treeFound = data.retrieveAvl(this.hash(ssn));
        if (treeFound == null) {
            System.out.println("Adding a new retiree");
            treeFound = new SsnAvl();
            success = treeFound.add(r);
        } else {
            if (treeFound.get(ssn) != null) {
                System.out.println("Social Security Number has been taken");
                System.out.println("Retiree will not be added");
                return false;
            }
            System.out.println("Adding a new retiree");
            success = treeFound.add(r);
        }
        data.saveAvl(index, treeFound);
        return success;
    }

    public boolean remove(long ssn) {
        //TODO - write this code
        SsnAvl treeFound;
        boolean success;
        int index = this.hash(ssn);
        treeFound = data.retrieveAvl(this.hash(ssn));
        if (treeFound == null) {
            success = false;
            System.out.println("there are no record to remove");
        } else {
            if (treeFound.get(ssn) == null) {
                System.out.println("there are no record to remove");
                return false;
            }
            success = treeFound.remove(treeFound.get(ssn));
            data.saveAvl(index, treeFound);
            System.out.println("Retiree is removed");
        }
        return success;
    }

    public int hash(long ssn) {
        int hashCode = (int) (ssn ^ (ssn >>> 32));
        return supplementalHash(hashCode) & (CAPACITY - 1);
    }

    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

}
