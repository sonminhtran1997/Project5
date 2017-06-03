package cs143.dataaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessFileImpl implements IDataAccess {
    
    @Override
    public boolean saveAvl(int index, SsnAvl avl) {
        String filename = "avl" + index + ".ser";
        try {
            //TODO - complete this code
            FileOutputStream ostream = new FileOutputStream(filename);
            ObjectOutputStream writer = new ObjectOutputStream(ostream);
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(DataAccessFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataAccessFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public SsnAvl retrieveAvl(int index) {
        String filename = "avl" + index + ".ser";
        SsnAvl treeResult = null;
        try {
            //TODO - complete this code
            FileInputStream istream = new FileInputStream(filename);
            ObjectInputStream reader = new ObjectInputStream(istream);
            treeResult =(SsnAvl) reader.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("The record for entered SSN does not exist");
        } catch (IOException ex) {
            System.out.println("The file from database cannot be read");
        } catch (ClassNotFoundException ex) {
            System.out.println("The data file is incorrect");
        }
        return treeResult;
    }
    
}
