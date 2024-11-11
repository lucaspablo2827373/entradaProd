package entradaProduto;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        // Testa a conexão com o banco de dados
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Conexão com o banco de dados foi bem-sucedida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
