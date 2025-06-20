import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // Tenta abrir uma conexão com o banco de dados SQLite.
    private static Connection connect() {
        String url = "jdbc:sqlite:db/data.db"; // Isso define a URL
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    // Método para executar um comando SQL
    public static boolean executeSQL(String sql) {
        boolean ok = false;
        
        Connection currentConnection = connect();
       
        try {
            Statement statement = currentConnection.createStatement();
            statement.execute(sql); // Executa o SQL passado como parâmetro
            currentConnection.close();
            ok = true;
        } catch (SQLException e1) {
            e1.printStackTrace();
            ok = false;
        }

        return ok;
    }

    public static boolean inserirRegistro(SQLClass registro) {
        // Gera a string SQL com insertSQL() (ex: INSERT INTO produtos (...) VALUES (...))
        // Execute usando o método executeSQL(). Retorna true se deu certo, false se deu erro
        return executeSQL(registro.insertSQL());
    }

    // Atualiza um objeto no banco de dados usando seu ID
    public static boolean atualizarRegistro(SQLClass registro) {
        return executeSQL(registro.updateSQL());
    }

    // Deleta um registro do banco, também usando seu ID
    public static boolean deletarRegistro(SQLClass registro) {
        return executeSQL(registro.deleteSQL());
    }

    // Código novo pra criar table se não tiver
    public static boolean criarTabela(SQLClass registro) {
        return executeSQL(registro.createTableSQL());
    }   


    public static boolean abrirID(SQLClass registro, int id) {
        Field[] fields =  registro.getClass().getDeclaredFields();
        boolean ok = false;

        Connection currentConnection = connect();
        try {
            PreparedStatement stmt = currentConnection.prepareStatement(registro.selectSQL() + " where id = " + id);
            ResultSet resultSet = stmt.executeQuery();

            for (Field field : fields) {
        field.setAccessible(true); // <-- ESSENCIAL pra acessar atributos private

        if (field.getType() == String.class) {
        field.set(registro, resultSet.getString(field.getName()));
        } else {
        field.set(registro, resultSet.getInt(field.getName()));
        }
}


            ok = true;

            currentConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        return ok;
    }
}