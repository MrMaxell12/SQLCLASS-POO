public class SQLFactory {
    public static void main (String[] args){
        
        Disciplina d = new Disciplina();
        d.setId(5);
        d.setNome("POO");
        d.setCargaHoraria(90);
        d.setCurso("ADS");
        d.setSemestre("5°");

        // Cria a table caso não exista! FUNCIONOOOOOOOOU É TETRA
        System.out.println(d.createTableSQL());
        Database.criarTabela(d);	

        System.out.println(d.insertSQL());
        Database.inserirRegistro(d);
        System.out.println("select na tabela:");
        Database.abrirID(d, 5);

        System.out.println("id: " + d.getId());
    }
}