package br.com.fatecmogidascruzes.topicos.noite;

import java.lang.reflect.Field;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class ServletFatec extends HttpServlet {

    public int getAsInt(HttpServletRequest request, String parametro) {
        return Integer.valueOf(request.getParameter(parametro));
    }

    public double getAsDouble(HttpServletRequest request, String parametro) {
        return Double.valueOf(request.getParameter(parametro));
    }

//    public <T> T getAsObject(HttpServletRequest request, Class<T> classe) {
//    }
    public void fillObject(HttpServletRequest request, Object objeto) throws IllegalArgumentException, IllegalAccessException {

        Class classeDoObjeto = objeto.getClass();
        Field[] atributos = classeDoObjeto.getDeclaredFields();
        for (Field atributo : atributos) {
            if (null != request.getParameter(atributo.getName())) {

                if (atributo.getType().getName().equals("int")
                        || atributo.getType().getName().equals("java.lang.Integer")) {

                    Integer valor = Integer.valueOf(request.getParameter(atributo.getName()));
                    atributo.setAccessible(true);
                    atributo.set(objeto, valor);

                } else if (atributo.getType().getName().equals("double")
                        || atributo.getType().getName().equals("java.lang.Double")) {

                    Double valor = Double.valueOf(request.getParameter(atributo.getName()));
                    atributo.setAccessible(true);
                    atributo.set(objeto, valor);

                }

            }
        }

    }

}
