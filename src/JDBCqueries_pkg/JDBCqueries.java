package JDBCqueries_pkg;

import java.sql.*;
import java.util.*;
import Instrument_pkg.Instrument;
import Cartridge_pkg.Cartridge;
import TestInstance_pkg.*;
import Errors_pkg.Errors;
import java.io.*;
//import java.nio.file.Files;
//import java.util.Date;

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
    ResultSetMetaData rsmd = null;

    public JDBCqueries() {

        try {
//            get JDBC ready for SQL queries
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            // handle the error
            System.out.println("Class Not Found Exception " + e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            // handle the error
            System.out.println("SQL Exception " + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("General Exception " + e.getMessage());
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

    public boolean isCartridgeValidToUse(String forCartID) {

        boolean result = false;

        // the following line is for test only
        // uncomment and put a previously used cardridege ID here to confirm test then fails
//        forCartID = "20171229111059589";
        try {
            // verify that cartridge ID is in database
            sql = "SELECT * FROM Cartridge_Manufactured WHERE cartridge_id = '" + forCartID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result = true;
            }

            //verofy that cartridge ID not used in previous test
            sql = "SELECT * FROM Clinical_Test_Instance WHERE cartridge_id = '" + forCartID + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result = false;
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
        return (result);
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

    public ArrayList getAllCartridgeIDs() {

        ArrayList<String> allCartIDs = new ArrayList<String>();

        try {

            sql = "SELECT cartridge_id FROM Cartridge_Manufactured";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                allCartIDs.add(rs.getString("cartridge_id"));
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
        return (allCartIDs);
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
                instrument.setCustomer_id(rs.getString("customer_id"));
                instrument.setCustomer_name(rs.getString("customer_name"));
                instrument.setCustomer_location(rs.getString("customer_location"));
                instrument.setContact_telephone(rs.getString("contact_telephone"));
                instrument.setContact_email(rs.getString("contact_email"));
                instrument.setCustomer_since(rs.getTimestamp("customer_since"));
                instrument.setAssay_types_enabled(rs.getInt("assay_types_enabled"));
                instrument.setCartridgeTrustMeAllowed(rs.getBoolean("cartridge_trustme_allowed"));
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
            boolean result = false;
            /*
             (cartridge_id, instrument_id, patient_id, technician_id, doctor_id, 
             raw_assay_data, analysis_result, clinical_test_timestamp)
             */


            sql = "INSERT INTO Clinical_Test_Instance "
                    + "(cartridge_id, instrument_id, patient_id, technician_id, doctor_id, raw_assay_data, analysis_result, clinical_test_timestamp) "
                    + "VALUES "
                    + "('" + test.getCartridge_id() + "', '" + test.getInstrument_id()
                    + "', '" + test.getPatient_id() + "', '" + test.getTechnician_id()
                    + "', '" + test.getDoctor_id() + "', '" + test.getImage_id_str()
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

    public long updateClinicalTestInstanceResultScore(TestInstance test) {

        try {
            boolean result = false;
            
            sql = "UPDATE Clinical_Test_Instance "
                    + "SET analysis_result = '" + test.getAnalysis_result() + "'"
                    + " WHERE clinical_test_instance_counter = '" + test.getClinical_test_instance_counter() + "'";

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

            return (test.getClinical_test_instance_counter());
        }   //end finally try
    }

    public long getClinicalTestImage(long clinical_test_instance_id, String targetFile) {

        long lengthOfFile = 0;
        ResultSet rset = null;

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
                    + clinical_test_instance_id + "'";
            PreparedStatement psmnt = conn.prepareStatement(sql);
            rset = psmnt.executeQuery();

            while (rset.next()) {
                blob = rset.getBlob("image");
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

    public boolean insertClinicalTestImages(DICOM dicom) {

        PreparedStatement psmnt = null;
        boolean result = false;

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

            List<TestImage> images = dicom.getTestImages();
            for (TestImage image : images) {

                File imageFile = new File(image.getTestImagePath());
                FileInputStream fis = new FileInputStream(imageFile);
                psmnt = conn.prepareStatement("INSERT INTO Clinical_Test_Images(image, image_timestamp) " + "VALUES(?,?)");
                psmnt.setBinaryStream(1, (InputStream) fis, (int) (imageFile.length()));
                psmnt.setString(2, currentTimestamp);
                int s = psmnt.executeUpdate();
                if (s > 0) {
                    sql = "SELECT * FROM Clinical_Test_Images WHERE image_timestamp = '" + currentTimestamp + "'";
                    rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        image.setClinicalTestImage_id(rs.getLong("clinical_test_image_counter"));
                        image.setClinicalTestImage_length(imageFile.length());
                    }
                    System.out.println("Image Uploaded successfully !");

                } else {
                    System.out.println("unsucessfull to upload image.");
                }

                psmnt.close();
                fis.close();
                result = true;
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

//            return (dicom.getClinicalTestImage_id());
            return result;

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

    public void getTestInstanceInfo(String testID, TestInstance test, boolean deleteImages) {

        try {
            sql = "SELECT * FROM Clinical_Test_Instance WHERE clinical_test_instance_counter = '" + testID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                test.setClinical_test_instance_counter(rs.getInt("clinical_test_instance_counter"));
                test.setCartridge_id(rs.getString("cartridge_id"));
                test.setInstrument_id(rs.getString("instrument_id"));
                test.setPatient_id(rs.getString("patient_id"));
                test.setTechnician_id(rs.getString("technician_id"));
                test.setDoctor_id(rs.getString("doctor_id"));
                test.setImage_id_str(rs.getString("raw_assay_data"));
                test.setAnalysis_result(rs.getFloat("analysis_result"));
                test.setClinical_test_timestamp(rs.getTimestamp("clinical_test_timestamp"));

                test.dicom.setPatient_id(test.getPatient_id());
                test.dicom.setTimestamp(test.getClinical_test_timestamp());

                List<String> idList = new ArrayList<>();
                idList = test.ImageIDstring2List();
                System.out.println("\nTest id: " + testID + " has " + test.ImageIDstring2List().size() + " images");

                String filePath = ".\\retrieved\\";
                File fileDir = new File(filePath);

                test.dicom.clearImageList();
                for (String id : idList) {
                    String fileName = "id_" + id + ".tif";

                    if (!fileDir.exists()) {
                        new File(filePath).mkdir();
                    }
                    TestImage testImage = new TestImage(filePath + fileName);
                    test.dicom.addTestImage(testImage);

                    testImage.setClinicalTestImage_id(Long.parseLong(id));

                    // following line causing SQL error
                    testImage.setClinicalTestImage_length(this.getClinicalTestImage(Long.parseLong(id), filePath + fileName));

                    System.out.println(testImage.toString());
                    System.out.println("Image ID is: " + id);

                    if (deleteImages) {
                        File f = new File(filePath + fileName);
                        if (f.exists() && !f.isDirectory()) {
                            f.delete();
                        }
                    }
                }
            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
//            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally try
    }

    public void getTestInstanceInfo_noImages(String testID, TestInstance test) {

        try {
            sql = "SELECT * FROM Clinical_Test_Instance WHERE clinical_test_instance_counter = '" + testID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                test.setClinical_test_instance_counter(rs.getInt("clinical_test_instance_counter"));
                test.setCartridge_id(rs.getString("cartridge_id"));
                test.setInstrument_id(rs.getString("instrument_id"));
                test.setPatient_id(rs.getString("patient_id"));
                test.setTechnician_id(rs.getString("technician_id"));
                test.setDoctor_id(rs.getString("doctor_id"));
                test.setImage_id_str(rs.getString("raw_assay_data"));
                test.setAnalysis_result(rs.getFloat("analysis_result"));
                test.setClinical_test_timestamp(rs.getTimestamp("clinical_test_timestamp"));

                test.dicom.setPatient_id(test.getPatient_id());
                test.dicom.setTimestamp(test.getClinical_test_timestamp());
                /*
                List<String> idList = new ArrayList<>();
                idList = test.ImageIDstring2List();
                System.out.println("\nTest id: " + testID + " has " + test.ImageIDstring2List().size() + " images");
                
                for (String id : idList) {
                    String fileName = "id_" + id + ".tif";
                    String filePath = ".\\retrieved\\";
                    File fileDir = new File(filePath);
                    if (!fileDir.exists()) {
                        new File(filePath).mkdir();
                    }
                    TestImage testImage = new TestImage(filePath + fileName);
                    test.dicom.addTestImage(testImage);
                    
                    testImage.setClinicalTestImage_id(Long.parseLong(id));

                    testImage.setClinicalTestImage_length(this.getClinicalTestImage(Long.parseLong(id), filePath + fileName));
   
                    System.out.println(testImage.toString());
                    System.out.println("Image ID is: " + id);
                }
                 */
            }

        } catch (SQLException e) {
            // handle the error
            System.out.println("\n" + "SQL Exception " + e.getMessage());
//            System.exit(0);
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally try
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

            sql = "SELECT error_counter FROM Errors WHERE error_timestamp = '" + error.getError_timestamp() + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                error.setError_counter(rs.getLong("error_counter"));
            }

//            sql = "SELECT error_counter FROM Errors WHERE error_timestamp > '" + "2017-04-04 16:00:00" + "'";            
//            rs = stmt.executeQuery(sql);
//            long counter = 0;
//            while (rs.next()) {
//                 counter = (rs.getLong("error_counter"));
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

    public void getErrorInfo(String errorID, Errors error) {

        try {
            sql = "SELECT * FROM Errors WHERE error_counter = '" + errorID + "'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                error.setError_counter(rs.getInt("error_counter"));
                error.setDescription(rs.getString("description"));
                error.setInstrument_id(rs.getString("instrument_id"));
                error.setCartridge_id(rs.getString("cartridge_id"));
                error.setTest_instance_id(rs.getString("test_instance_id"));
                error.setError_code(rs.getString("error_code"));
                error.setError_timestamp(rs.getTimestamp("error_timestamp"));
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

    // general
    public ArrayList getTableColumnNames(String tableName) {

        ArrayList<String> columnNames = new ArrayList<String>();

        try {

            rs = stmt.executeQuery("SELECT * FROM " + tableName);
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // The column count starts from 1
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
                // Do stuff with name
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

        }   //end finally
        return (columnNames);
    }
}
