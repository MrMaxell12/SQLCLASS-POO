public class Produto extends SQLClass {
    @Key
    int id; // Identifica o produto no banco (chave primária)
    // Descrição do produto (ex: "Arroz")
    String descricao;
    double preco; // Preço do produto (ex: 5.99)
    String um; // Unidade de medida (ex: "kg", "un")

    Produto() {
        // Define o nome da tabela como "produtos" para
        // que os métodos da SQLClass saibam para onde gerar os comandos SQL.
        this.setTableName("PRODUTOS");
    }

    /*
     * A classe Produto herda da classe base SQLClass, que já possui:
     * métodos para gerar SQL (insertSQL(), updateSQL(), etc.),
     * lógica de reflexão para ler campos e valores,
     * suporte para a anotação @Key.
     */
}
