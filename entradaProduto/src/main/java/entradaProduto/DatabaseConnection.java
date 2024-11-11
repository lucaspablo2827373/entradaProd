package entradaProduto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL de conexão com o banco MariaDB
    private static final String URL = "jdbc:mariadb://localhost:3306/estoque";  // Altere conforme o nome do seu banco
    private static final String USER = "root"; // Usuário do banco de dados, geralmente 'root'
    private static final String PASSWORD = "eoMAo1#$"; // A senha do usuário root ou o outro que você configurou

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        try {
            // Carregar o driver JDBC do MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Retorna a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado!", e);
        }
    }
}


