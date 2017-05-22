/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors_pkg;

//import java.util.Date;
import Cartridge_pkg.Cartridge;
import Instrument_pkg.Instrument;
import TestInstance_pkg.TestInstance;
import java.sql.*;

/**
 *
 * @author Owner
 */
public class Errors {
    
    final public String ErrorCode_CartridgeNotCampatibleWithInstrument = "1001";

    /*
    CREATE TABLE Instrument_Error (
       error_counter BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
       description TEXT,
       instrument_id VARCHAR (20),
       cartridge_id VARCHAR (20),
       test_instance_id VARCHAR (20),
       error_code VARCHAR (20),
       error_timestamp TIMESTAMP,
       PRIMARY KEY (error_counter )
);  */
    long error_counter = 0;
    String description = null;
    String instrument_id = null;
    String cartridge_id = null;
    String test_instance_id = null;
    String error_code = null;
    Timestamp  error_timestamp = null;
//    String error_timestamp = null;

    @Override
    public String toString() {
        return "Errors:"
                + "\n   error_counter= \t" + error_counter
                + "\n   description= \t\t" + description
                + "\n   instrument_id= \t" + instrument_id
                + "\n   cartridge_id= \t\t" + cartridge_id
                + "\n   test_instance_id=\t" + test_instance_id
                + "\n   error_code= \t\t" + error_code
                + "\n   error_timestamp= \t" + error_timestamp
                + "\n";
    }

    public long getError_counter() {
        return error_counter;
    }

    public void setError_counter(long error_counter) {
        this.error_counter = error_counter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getCartridge_id() {
        return cartridge_id;
    }

    public void setCartridge_id(String cartridge_id) {
        this.cartridge_id = cartridge_id;
    }

    public String getTest_instance_id() {
        return test_instance_id;
    }

    public void setTest_instance_id(String test_instance_id) {
        this.test_instance_id = test_instance_id;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public Timestamp getError_timestamp() {
        return error_timestamp;
    }

    public void setError_timestamp(Timestamp error_timestamp) {
        this.error_timestamp = error_timestamp;
    }

    public void buildErrorObject_CartridgeNotCampatibleWithInstrument(String instrument_id,
            String cartridge_id,
            String testInstance_id) {

        try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            this.setDescription("Cartridge is not compatible with assay tests supported by this Instrument");
            this.setInstrument_id(instrument_id);
            this.setCartridge_id(cartridge_id);
            this.setTest_instance_id(testInstance_id);
            this.setError_code(ErrorCode_CartridgeNotCampatibleWithInstrument);
            this.setError_timestamp(new Timestamp(System.currentTimeMillis()));

        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {

        }   //end finally 
    }
}
