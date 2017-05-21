/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors_pkg;

import java.util.Date;

/**
 *
 * @author Owner
 */
public class Errors {
    /*
    CREATE TABLE Instrument_Error (
       error_counter BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
       description TEXT,
       instrument_id VARCHAR (20),
       cartridge_id VARCHAR (20),
       test_instance_id VARCHAR (20),
       instrument_error_code VARCHAR (20),
       instrument_error_timestamp TIMESTAMP,
       PRIMARY KEY (error_counter )
);  */
    long error_counter = 0;
    String description = null;
    String instrument_id = null;
    String cartridge_id = null;
    String test_instance_id = null;
    String instrument_error_code = null;
    Date instrument_error_timestamp = null;

    @Override
    public String toString() {
        return "Errors:"
                + "\n   error_counter= \t\t" + error_counter 
                + "\n   description= \t\t" + description 
                + "\n   instrument_id= \t\t" + instrument_id 
                + "\n   cartridge_id= \t\t" + cartridge_id 
                + "\n   test_instance_id=\t\t" + test_instance_id 
                + "\n   instrument_error_code= \t\t" + instrument_error_code 
                + "\n   instrument_error_timestamp= \t\t" + instrument_error_timestamp 
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

    public String getInstrument_error_code() {
        return instrument_error_code;
    }

    public void setInstrument_error_code(String instrument_error_code) {
        this.instrument_error_code = instrument_error_code;
    }

    public Date getInstrument_error_timestamp() {
        return instrument_error_timestamp;
    }

    public void setInstrument_error_timestamp(Date instrument_error_timestamp) {
        this.instrument_error_timestamp = instrument_error_timestamp;
    }
}
