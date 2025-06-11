public class Aluno extends SQLClass {
    @Key
    int id; // Campo de Identificação (Primary Key)
    String nome; // Nome do Aluno
    int idade; // Idade do aluno
    String cpf; // Campo CPF
    String curso; // Curso do Aluno

    Aluno() {

            this.setTableName("ALUNOS");

    }
}
