public class SQLFactory {
    public static void main (String[] args){
        
        Aluno a = new Aluno();
        a.id = 1;
        a.nome = "João Pedro Ianke B. de Lima";
        a.idade = 22;
        a.cpf = "067.369.931-56";
        a.curso = "ADS";

        // Cria a table caso não exista!
        System.out.println(a.createTableSQL());
        Database.criarTabela(a);

        System.out.println(a.insertSQL());
        Database.inserirRegistro(a);
        System.out.println("select na tabela:");
        Database.abrirID(a, 1);

        System.out.println("id: " + a.id);

        
    }
}   
