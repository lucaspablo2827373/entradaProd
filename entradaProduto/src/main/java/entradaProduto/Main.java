package entradaProduto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criando a interface de usuário
        Label lblIdProduto = new Label("ID do Produto:");
        TextField txtIdProduto = new TextField();
        txtIdProduto.setPromptText("Digite o ID do produto");

        Label lblQuantidade = new Label("Quantidade:");
        TextField txtQuantidade = new TextField();
        txtQuantidade.setPromptText("Digite a quantidade");

        Button btnAdicionar = new Button("Adicionar ao Estoque");
        Button btnRemover = new Button("Remover do Estoque");

        // Definindo a ação dos botões
        btnAdicionar.setOnAction(e -> {
            String idProduto = txtIdProduto.getText();
            String quantidadeStr = txtQuantidade.getText();
            if (!idProduto.isEmpty() && !quantidadeStr.isEmpty()) {
                int quantidade = Integer.parseInt(quantidadeStr);
                adicionarEstoque(idProduto, quantidade);
            }
        });

        btnRemover.setOnAction(e -> {
            String idProduto = txtIdProduto.getText();
            String quantidadeStr = txtQuantidade.getText();
            if (!idProduto.isEmpty() && !quantidadeStr.isEmpty()) {
                int quantidade = Integer.parseInt(quantidadeStr);
                removerEstoque(idProduto, quantidade);
            }
        });

        // Organizando os elementos na interface
        VBox root = new VBox(10);
        root.getChildren().addAll(lblIdProduto, txtIdProduto, lblQuantidade, txtQuantidade, btnAdicionar, btnRemover);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Controle de Estoque");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Função para adicionar quantidade ao estoque
    private void adicionarEstoque(String idProduto, int quantidade) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, quantidade);
                stmt.setString(2, idProduto);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Quantidade adicionada com sucesso!");
                } else {
                    System.out.println("Produto não encontrado!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para remover quantidade do estoque
    private void removerEstoque(String idProduto, int quantidade) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, quantidade);
                stmt.setString(2, idProduto);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Quantidade removida com sucesso!");
                } else {
                    System.out.println("Produto não encontrado!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
