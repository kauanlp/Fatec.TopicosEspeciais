package br.com.fatecmogidascruzes.topicosbackend1;

import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Coluna;
import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Id;
import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Tabela;
import br.com.fatecmogidascruzes.topicosbackend1.persistencia.anotacoes.Transiente;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class DAOGenerico {

    public void salvar(Object objeto) throws ClassNotFoundException, SQLException, IllegalAccessException {
        String sql = "INSERT INTO ";

        Tabela tabela = objeto.getClass().getDeclaredAnnotation(Tabela.class);
        if(null == tabela || Tabela.TABELA_SEM_NOME.equals(tabela.nome())) {
            sql += "_" + objeto.getClass().getSimpleName().toLowerCase();
        } else {
            sql += tabela.nome();
        }
        sql += "(";

        String fimSQL = " VALUES(";

        Field[] atributos = objeto.getClass().getDeclaredFields();
        for(Field atributo : atributos) {
            Transiente transiente = atributo.getDeclaredAnnotation(Transiente.class);
            Id id = atributo.getDeclaredAnnotation(Id.class);

            if(null == transiente && null == id) {
                Coluna coluna = atributo.getDeclaredAnnotation(Coluna.class);
                if(null == coluna || Coluna.COLUNA_SEM_NOME.equals(coluna.nome())) {
                    sql += atributo.getName().toLowerCase();
                } else {
                    sql += coluna.nome();
                }
                sql += ", ";
                fimSQL += "?, ";
            }
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += ")";

        fimSQL = fimSQL.substring(0, fimSQL.length() - 2);
        fimSQL += ")";

        sql += fimSQL;

        // INSERT INTO _classe(atributo1, atributo2, atributo3) VALUES (?, ?, ?)

        System.out.println(sql);
        /*
        try (Connection conexao = BancoDados.getConexao()) {
            int numeroInterrogacao = 1;
            PreparedStatement sqlPreparado = conexao.prepareStatement(sql);
            for(Field atributo : atributos) {
                Id id = atributo.getDeclaredAnnotation(Id.class);
                if(null == id) {
                    atributo.setAccessible(true);
                    Object valorAtributo = atributo.get(objeto);
                    sqlPreparado.setObject(numeroInterrogacao++, valorAtributo);
                }
            }
            sqlPreparado.executeUpdate();
        }*/

    }

}
