import java.lang.reflect.Field;

public abstract class SQLClass {
    // Aqui temos as strings sendo declaradas

    //A tableName vai receber o nome da Array do SQL
    private String tableName;
    //A fieldsList vai receber 
    private String fieldsList;
    private String listValues;
    private String fieldsValuesList;
    
    //
    public void setTableName (String tableName) {
        this.tableName = tableName;
    }

    // Esse listFields() vai estar pegando os campos e convertendo para string SQL ou seja, com separação de "," e etc
    protected void listFields(){
        //Vai estar coletando cada campo e pondo elas dentro do array Field[] que vai ser usado no 4° comentário
        Field[] fields = this.getClass().getDeclaredFields();

        //Declarando a string onde os campos serão adicionados
        String fieldsString = "";
        String valuesString = "";

        // Aqui rola um loop for pra que ele fique pegando cada campo presente no array Field[], transformando numa string e adicionando ao final dela uma ","
        for (Field field : fields) {
            fieldsString = fieldsString + ", " + field.getName();

            String FieldValue = ""; 

            try {
                FieldValue = FixFieldType(field);
            } catch (Exception e) {
                e.printStackTrace();
            }

            valuesString = valuesString + ", " + FieldValue;
        }

        this.fieldsList = fieldsString.length() > 0 ? fieldsString.substring(2) : "";
        this.listValues = valuesString.length() > 0 ? valuesString.substring(2) : "";
    }

    //Aqui ele vai pegar os valores dos campos 
    protected void listFieldValues(){
        Field[] fields= this.getClass().getDeclaredFields();

        String fieldsValuesString = "";

        for (Field field : fields) {
            String FieldValue = "";

            try {
                FieldValue = FixFieldType(field);
            } catch (Exception e) {
                e.printStackTrace();
            }

            fieldsValuesString = fieldsValuesString + ", " + field.getName() + " = " + FieldValue;
        }

        this.fieldsValuesList = fieldsValuesString.length() > 0 ? fieldsValuesString.substring(2) : "";
    }

    //getkey descobre se tem chaves no Field[], seja ela primaria ou estrangeira.
    public String getKey(){
        Field[] fields = this.getClass().getDeclaredFields();

        String keyName = "";
        for (Field field : fields){
            if (field.isAnnotationPresent(Key.class)) {
                keyName = field.getName();
            }
        }

        return keyName;
    }

    //Aqui ele vai salva na variavel 
    public int getValueKey(){
        int keyValue = 0;
        try {
            Field keyField = this.getClass().getDeclaredField(this.getKey());
            keyField.setAccessible(true); // necessário para acessar campo private
            Object value = keyField.get(this);
            keyValue = (int) value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyValue;
    }

    //FixFieldType vai ler o tipo do campo Field e se for diferente de string, vai estar convertendo, caso já seja, ele só vai adicionar o conteudo
    // Exception é uma biblioteca que vai estar mantendo o sistema de pé caso dê um erro, sem ele o sistema simplesmente pararia
    private String FixFieldType(Field field) throws Exception {
        field.setAccessible(true); // necessário para acessar campo private
        String value = "";
        Object valueObject = field.get(this);

        if (field.getType() == String.class) {
            value = value + "'" + valueObject +"'";
        } else {
            value = value + valueObject;
        }

        return value;
    }

    // HORA DE DECLARAR AS CLASSES PRO "CRUD" 

    //Pega o nome da tabela
    public String getTableName(){
        return tableName;
    }

    public String selectSQL(){
        return "select * from "+this.tableName;
    } 

    // Linha adicionada pra criar table se não existir
    public String createTableSQL(){
        return "create table if not exists "+ this.tableName + " ("+this.fieldsList+")";
    }

    public String insertSQL(){
        this.listFields();
        return "insert into "+ this.tableName + " ("+this.fieldsList+") values ("+this.listValues+")";
        
    }

    public String deleteSQL(){
        return "delete from "+this.tableName + " where " + this.getKey() +" = " + this.getValueKey();
    }

    public String updateSQL(){
        this.listFieldValues();
        return "update " + this.tableName + " set " + this.fieldsValuesList+ " where " + this.getKey() + " = " + this.getValueKey();
    }
}
