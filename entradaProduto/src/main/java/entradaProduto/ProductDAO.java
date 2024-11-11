package entradaProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    // Função para adicionar quantidade ao estoque
    public void adicionarQuantidade(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantidade adicionada com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar quantidade: " + e.getMessage());
        }
    }

    // Função para remover quantidade do estoque
    public void removerQuantidade(int idProduto, int quantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Verifica a quantidade atual antes de remover
            int quantidadeAtual = getQuantidadeProduto(idProduto);
            if (quantidadeAtual < quantidade) {
                System.out.println("Quantidade insuficiente no estoque.");
                return;
            }

            stmt.setInt(1, quantidade);
            stmt.setInt(2, idProduto);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quantidade removida com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover quantidade: " + e.getMessage());
        }
    }

    // Função para obter a quantidade atual de um produto
    private int getQuantidadeProduto(int idProduto) {
        String sql = "SELECT quantidade FROM produtos WHERE id = ?";
        int quantidade = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                quantidade = resultSet.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar quantidade: " + e.getMessage());
        }

        return quantidade;
    }
}
