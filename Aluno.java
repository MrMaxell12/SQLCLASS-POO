public class Aluno extends SQLClass {
    @Key
    private int id; // Campo de Identificação (Primary Key)
    private String nome; // Nome do Aluno
    private int idade; // Idade do aluno
    private String cpf; // Campo CPF
    private String curso; // Curso do Aluno
    

    public Aluno() {
        this.setTableName("ALUNOS");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
