**Comandos para o SQLFactory para criar as listas dentro do DB**

//Aluno
public class SQLFactory {
    public static void main (String[] args){
        
        Aluno a = new Aluno();
        a.setId(//Botaoid);
        a.setNome("Deodoro da Fonseca");
        a.setIdade(30);
        a.setCpf("282.231.888-55");
        a.setCurso("ADS");

        // Cria a table caso não exista! FUNCIONOOOOOOOOU É TETRA
        System.out.println(a.createTableSQL());
        Database.criarTabela(a);

        System.out.println(a.insertSQL());
        Database.inserirRegistro(a);
        System.out.println("select na tabela:");
        Database.abrirID(a, //Botaoid);

        System.out.println("id: " + a.getId());

        
    }
}   

//Professor

public class SQLFactory {
    public static void main (String[] args){
        
        Professor p = new Professor();
        p.setId(//Botaid);
        p.setNome("Deodoro da Fonseca");
        p.setIdade(30);
        p.setTelefone("(66) 9 9292-9292");
        p.setEspecialidade("Banco de Dados");

        // Cria a table caso não exista! FUNCIONOOOOOOOOU É TETRA
        System.out.println(p.createTableSQL());
        Database.criarTabela(p);	

        System.out.println(p.insertSQL());
        Database.inserirRegistro(p);
        System.out.println("select na tabela:");
        Database.abrirID(p, //Botaid);

        System.out.println("id: " + p.getId());

        
    }
}   

//Disciplina

public class SQLFactory {
    public static void main (String[] args){
        
        Disciplina d = new Disciplina();
        d.setId(1);
        d.setNome("Robotica");
        d.setCargaHoraria(60);
        d.setCurso("ADS");
        d.setSemestre("1°");

        // Cria a table caso não exista! FUNCIONOOOOOOOOU É TETRA
        System.out.println(d.createTableSQL());
        Database.criarTabela(d);	

        System.out.println(d.insertSQL());
        Database.inserirRegistro(d);
        System.out.println("select na tabela:");
        Database.abrirID(d, 1);

        System.out.println("id: " + d.getId());
    }
}



