package ModeloException;

import java.sql.SQLException;

public class AppException extends Exception{

    public AppException(SQLException e,String sql,String mensaje) {
        super(e);
    }

    public AppException(Exception e, String message, String message2) {
        super(e);
    }
}
