public class SQLFactory {
    public static void main (String[] args){

        Produto p = new Produto();
        p.id = 11;
    
        p.descricao = "Café 500g";
        p.preco = 9.90;
        p.um = "pct";

        System.out.println(p.insertSQL());
    
        Database.inserirRegistro(p);
        System.out.println("select na tabela:");
        Database.abrirID(p, 11);

        System.out.println("id: " + p.id);
    }
}   
