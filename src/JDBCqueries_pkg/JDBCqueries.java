package JDBCqueries_pkg;

import java.sql.*;
import java.util.*;
import Instrument_pkg.Instrument;
import Cartridge_pkg.Cartridge;
import TestInstance_pkg.*;
import Errors_pkg.Errors;
import java.io.*;
import java.util.Date;

/**
 *
 * @author David Kloosterman
 */
public class JDBCqueries {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sensodx_sql_db?useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "rootMysql151";

    Connection conn = null;
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;

    public JDBCqueries() {

        String display = null;

        try {
//            get JDBC ready for SQL queries
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            // handle the error
            display += "\n" + "Class Not Found Exception " + e.getMessage();
            System.exit(0);
        } catch (SQLException e) {
            // handle the error
            display += "\n" + "SQL Exception " + e.getMessage();
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            display += "\n" + "General Exception " + e.getMessage();
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally
    }

    // Cartridge queries
    public void getCartridgeMfgInfo(String forCartID, Cartridge cartridge) {

        try {

            // get and display data for seleted Instrument ID
            sql = "SELECT * FROM Cartridge_Manufactured WHERE cartridge_id = '" + forCartID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cartridge.setCartridge_id(rs.getString("cartridge_id"));
                cartridge.setManufactured_timestamp(rs.getTimestamp("manufactured_timestamp"));
                cartridge.setDeployment_type((String) rs.getString("deployment_type"));
                cartridge.setManufactured_location(rs.getString("manufactured_location"));
                cartridge.setAssay_type(rs.getInt("assay_type"));
                cartridge.setSubsystem_1_id(rs.getString("subsystem_1_id"));
                cartridge.setSubsystem_2_id(rs.getString("subsystem_2_id"));
                cartridge.setSubsystem_3_id(rs.getString("subsystem_3_id"));
            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally try
    }

    public ArrayList getAllCartridgeIDs() {

        ArrayList<String> allIDs = new ArrayList<String>();

        try {

            sql = "SELECT cartridge_id FROM Cartridge_Manufactured";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                allIDs.add(rs.getString("cartridge_id"));
            } // end while (rs.next()) 

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally
        return (allIDs);
    }

    public void insertCartridge(Cartridge cartridge) {

        try {
            sql = "INSERT INTO Cartridge_Manufactured"
                    + "(cartridge_id, manufactured_timestamp, deployment_type, "
                    + "manufactured_location, assay_type, "
                    + "subsystem_1_id, subsystem_2_id, subsystem_3_id)"
                    + " VALUES "
                    + "('" + cartridge.getCartridge_id()
                    + "', '" + cartridge.getManufactured_timestamp()
                    + "', '" + cartridge.getDeployment_type()
                    + "', '" + cartridge.getManufactured_location()
                    + "', '" + cartridge.getAssay_type()
                    + "', '" + cartridge.getSubsystem_1_id()
                    + "', '" + cartridge.getSubsystem_2_id()
                    + "', '" + cartridge.getSubsystem_3_id() + "')";

            // get and display data for seleted Instrument ID
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally try
    }

    // Instrument quesries
    public void getInstrumentDeploymentInfo(String instrID, Instrument instrument) {

        try {
            // get and display data for seleted Instrument ID
            sql = "SELECT * FROM Instrument_Deployed WHERE instrument_id = '" + instrID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                instrument.setInstrument_id(rs.getString("instrument_id"));
                instrument.setInstallation_timestamp(rs.getTimestamp("installation_timestamp"));
                instrument.setDeployment_type(rs.getString("deployment_type"));

                // example of how to use the Instrument.DeploymentType enum
//                if(instrument.getDeployment_type().equals(Instrument.DeploymentType.LDT.toString())){
//                    System.out.println("a deployment to LDT");
//                }
//                System.out.println("enum value: >" + Instrument.DeploymentType.LDT + "<");
//                System.out.println("instrument value: >" + instrument.getDeployment_type() + "<");
                instrument.setCustomer_id(rs.getString("customer_id"));
                instrument.setCustomer_name(rs.getString("customer_name"));
                instrument.setCustomer_location(rs.getString("customer_location"));
                instrument.setContact_telephone(rs.getString("contact_telephone"));
                instrument.setContact_email(rs.getString("contact_email"));
                instrument.setCustomer_since(rs.getTimestamp("customer_since"));
                instrument.setAssay_types_enabled(rs.getInt("assay_types_enabled"));
            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally try
    }

    public ArrayList getAllInstrumentIDs() {

        ArrayList<String> allInstrIDs = new ArrayList<String>();

        try {

            sql = "SELECT instrument_id FROM Instrument_Manufactured";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                allInstrIDs.add(rs.getString("instrument_id"));
            } // end while (rs.next()) 

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally
        return (allInstrIDs);
    }

    public void getInstrumentMfgInfo(String forInstrID, Instrument instrument) {

        try {
            // get and display data for seleted Instrument ID
            sql = "SELECT * FROM Instrument_Manufactured WHERE instrument_id = '" + forInstrID + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                instrument.setInstrument_id(rs.getString("instrument_id"));
                instrument.setManufactured_timestamp(rs.getTimestamp("manufactured_timestamp"));
                instrument.setManufactured_location(rs.getString("manufactured_location"));
                instrument.setSubsystem_1_id(rs.getString("subsystem_1_id"));
                instrument.setSubsystem_2_id(rs.getString("subsystem_2_id"));
                instrument.setSubsystem_3_id(rs.getString("subsystem_3_id"));
            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally try
    }

    // Test Instance queries
    public long insertClinicalTestInstance(TestInstance test) {

        try {
            /*
             (cartridge_id, instrument_id, patient_id, technician_id, doctor_id, 
             raw_assay_data, analysis_result, clinical_test_timestamp)
             */
            sql = "INSERT INTO Clinical_Test_Instance "
                    + "(cartridge_id, instrument_id, patient_id, technician_id, doctor_id, raw_assay_data, analysis_result, clinical_test_timestamp) "
                    + "VALUES "
                    + "('" + test.getCartridge_id() + "', '" + test.getInstrument_id()
                    + "', '" + test.getPatient_id() + "', '" + test.getTechnician_id()
                    + "', '" + test.getDoctor_id() + "', '" + test.getRaw_assay_data()
                    + "', '" + test.getAnalysis_result()
                    + "', '" + test.getClinical_test_timestamp() + "')";

            // get and display data for seleted Instrument ID
            stmt.executeUpdate(sql);

            sql = "SELECT * FROM Clinical_Test_Instance WHERE cartridge_id = '" + test.getCartridge_id() + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                test.setClinical_test_instance_counter(rs.getLong("clinical_test_instance_counter"));

            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

            return (test.getClinical_test_instance_counter());
        }   //end finally try
    }

    public long getClinicalTestImage(long clinical_test_instance_counter, String targetFile) {

        long lengthOfFile = 0;

        try {
            /*
                CREATE TABLE Clinical_Test_Images (
                       clinical_test_image_counter        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       image           blob,
                       image_timestamp VARCHAR (25),
                       PRIMARY KEY (image_id )
                );
             */
            File file = new File(targetFile);
            FileOutputStream fos = new FileOutputStream(file);
            byte b[];
            Blob blob;

            sql = "SELECT * FROM Clinical_Test_Images WHERE clinical_test_image_counter = '"
                    + clinical_test_instance_counter + "'";
            PreparedStatement psmnt = conn.prepareStatement(sql);
            rs = psmnt.executeQuery();

            while (rs.next()) {
                blob = rs.getBlob("image");
                b = blob.getBytes(1, (int) blob.length());
                fos.write(b);
                lengthOfFile = blob.length();
            }

            psmnt.close();
            fos.close();

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

            return (lengthOfFile);
        }
    }

    public long insertClinicalTestImage(DICOM dicom) {

        PreparedStatement psmnt = null;

        try {
            /*
                CREATE TABLE Clinical_Test_Images (
                       clinical_test_image_counter        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       image           blob,
                       image_timestamp VARCHAR (25),
                       PRIMARY KEY (image_id )
                );
             */
            String currentTimestamp = dicom.getTimestamp().toString();
            currentTimestamp = currentTimestamp.replace(" ", "");
            currentTimestamp = currentTimestamp.replace(":", "");
            currentTimestamp = currentTimestamp.replace(".", "");
            currentTimestamp = currentTimestamp.replace("-", "");

            File imageFile = new File(dicom.getClinicalTestFilePathInInstrument());
            FileInputStream fis = new FileInputStream(imageFile);
            psmnt = conn.prepareStatement("INSERT INTO Clinical_Test_Images(image, image_timestamp) " + "VALUES(?,?)");
            psmnt.setBinaryStream(1, (InputStream) fis, (int) (imageFile.length()));
            psmnt.setString(2, currentTimestamp);
            int s = psmnt.executeUpdate();
            if (s > 0) {
                sql = "SELECT * FROM Clinical_Test_Images WHERE image_timestamp = '" + currentTimestamp + "'";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    dicom.setClinicalTestImage_id(rs.getLong("clinical_test_image_counter"));
                    dicom.setClinicalTestImage_length(imageFile.length());
                }
                System.out.println("Image Uploaded successfully !");

            } else {
                System.out.println("unsucessfull to upload image.");
            }

            psmnt.close();
            fis.close();

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

            return (dicom.getClinicalTestImage_id());
        }   //end finally try
    }

    public ArrayList getAllTestInstanceIDs() {

        ArrayList<String> allIDs = new ArrayList<String>();

        try {

            sql = "SELECT clinical_test_instance_counter FROM Clinical_Test_Instance";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ID_str = String.valueOf(rs.getInt("clinical_test_instance_counter"));
                allIDs.add(ID_str);
            } // end while (rs.next()) 

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally
        return (allIDs);
    }

    // Error queries
    public long insertError(Errors error) {

        try {
            /*
             (description, instrument_id, cartridge_id, 
                     test_instance_id, error_code, error_timestamp)
             */
            sql = "INSERT INTO Errors "
                    + "(description, instrument_id, cartridge_id, "
                    + "test_instance_id, error_code, error_timestamp) "
                    + "VALUES "
                    + "('" + error.getDescription() + "', '" + error.getInstrument_id()
                    + "', '" + error.getCartridge_id() + "', '" + error.getTest_instance_id()
                    + "', '" + error.getError_code() + "', '" + error.getError_timestamp()
                    + "')";

            // get and display data for seleted Instrument ID
            stmt.executeUpdate(sql);

//            sql = "SELECT error_counter FROM Errors";
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                error.setError_counter(rs.getLong("error_counter"));
//            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

            return (error.getError_counter());
        }   //end finally try
    }

    public ArrayList getAllErrorIDs() {

        ArrayList<String> allIDs = new ArrayList<String>();

        try {

            sql = "SELECT error_counter FROM Errors";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                allIDs.add(rs.getString("error_counter"));
            } // end while (rs.next()) 

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally
        return (allIDs);
    }

}
