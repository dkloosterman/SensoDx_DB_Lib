/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrument_pkg;

import TestInstance_pkg.*;
import static TestInstance_pkg.TestInstance.ASSAY_FIELD_LENGTH;
import java.util.Date;

/**
 *
 * @author David Kloosterman
 */
public class Instrument {
    
    public enum DeploymentType {
        LDT, ResearchAndDevelopment, Product, Manufacturing
    };
//    DeploymentType objectType = DeploymentType.INSTRUMENT;


    /*  This is what the database tables look like
    CREATE TABLE Instrument_Manufactured ( 
       instrument_id VARCHAR (20),
       manufactured_timestamp TIMESTAMP,
       manufactured_location TEXT,
       subsystem_1_id VARCHAR (20),
       subsystem_2_id VARCHAR (20),
       subsystem_3_id VARCHAR (20),
       PRIMARY KEY (instrument_id ) );
    
    CREATE TABLE Instrument_Deployed (
       instrument_id VARCHAR (20),
       installation_timestamp TIMESTAMP,
       deployment_type VARCHAR (25),
       customer_id VARCHAR (20),
       customer_name VARCHAR (50),
       customer_location TEXT,
       contact_name VARCHAR (50),
       contact_telephone VARCHAR (25),
       contact_email VARCHAR (50),
       customer_since DATE DEFAULT '0000-00-00',
       assay_types_enabled VARCHAR (50),
       PRIMARY KEY (instrument_id )  );
     */
    // Instrument Mfg. Info
    String instrument_id = null;
    Date manufactured_timestamp = null;
    String manufactured_location = null;
    String subsystem_1_id = null;
    String subsystem_2_id = null;
    String subsystem_3_id = null;

    // Instrument Deployment Info
    //    String instrument_id;
    Date installation_timestamp = null;
    String deployment_type = null;
    String customer_id = null;
    String customer_name = null;
    String customer_location = null;
    String contact_telephone = null;
    String contact_email = null;
    Date customer_since = null;
    int assay_types_enabled = 0;

    public Instrument() {

    }

    @Override
    public String toString() {

        String binaryAssayTypesEnabled = TestInstance.convertIntegerToBinaryString(assay_types_enabled, ASSAY_FIELD_LENGTH);

        return "Instrument Manufacturing Information"
                + "\n   instrument_id =\t" + instrument_id
                + "\n   manufactured_timestamp =\t" + manufactured_timestamp
                + "\n   manufactured_location =\t" + manufactured_location
                + "\n   subsystem_1_id =\t" + subsystem_1_id
                + "\n   subsystem_2_id =\t" + subsystem_2_id
                + "\n   subsystem_3_id =\t" + subsystem_3_id
                + "\n\nInstrument Deployment Information"
                + "\n   installation_timestamp =\t" + installation_timestamp
                + "\n   deployment_type =\t" + deployment_type
                + "\n   customer_id =\t" + customer_id
                + "\n   customer_name =\t" + customer_name
                + "\n   customer_location =\t" + customer_location
                + "\n   contact_telephone =\t" + contact_telephone
                + "\n   contact_email =\t" + contact_email
                + "\n   customer_since =\t" + customer_since
                + "\n   assay_types_enabled =\t" + binaryAssayTypesEnabled;
    }

    public Date getManufactured_timestamp() {
        return manufactured_timestamp;
    }

    public void setManufactured_timestamp(Date manufactured_timestamp) {
        this.manufactured_timestamp = manufactured_timestamp;
    }

    public String getManufactured_location() {
        return manufactured_location;
    }

    public void setManufactured_location(String manufactured_location) {
        this.manufactured_location = manufactured_location;
    }

    public String getSubsystem_1_id() {
        return subsystem_1_id;
    }

    public void setSubsystem_1_id(String subsystem_1_id) {
        this.subsystem_1_id = subsystem_1_id;
    }

    public String getSubsystem_2_id() {
        return subsystem_2_id;
    }

    public void setSubsystem_2_id(String subsystem_2_id) {
        this.subsystem_2_id = subsystem_2_id;
    }

    public String getSubsystem_3_id() {
        return subsystem_3_id;
    }

    public void setSubsystem_3_id(String subsystem_3_id) {
        this.subsystem_3_id = subsystem_3_id;
    }

    public Date getInstallation_timestamp() {
        return installation_timestamp;
    }

    public void setInstallation_timestamp(Date installation_timestamp) {
        this.installation_timestamp = installation_timestamp;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_location() {
        return customer_location;
    }

    public void setCustomer_location(String customer_location) {
        this.customer_location = customer_location;
    }

    public String getContact_telephone() {
        return contact_telephone;
    }

    public void setContact_telephone(String contact_telephone) {
        this.contact_telephone = contact_telephone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public Date getCustomer_since() {
        return customer_since;
    }

    public void setCustomer_since(Date customer_since) {
        this.customer_since = customer_since;
    }

    public int getAssay_types_enabled() {
        return assay_types_enabled;
    }

    public void setAssay_types_enabled(int assay_types_enabled) {
        this.assay_types_enabled = assay_types_enabled;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getDeployment_type() {
        return deployment_type;
    }

    public void setDeployment_type(String deployment_type) {
        this.deployment_type = deployment_type;
    }

}
