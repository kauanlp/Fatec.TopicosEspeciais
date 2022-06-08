package br.com.fatecmogidascruzes.topicos.noite;

import br.com.fatecmogidascruzes.topicos.noite.anotacao.Controladora;
import br.com.fatecmogidascruzes.topicos.noite.anotacao.MapeamentoRequisicao;
import br.com.fatecmogidascruzes.topicos.noite.anotacao.ParametroRequisicao;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends ServletFatec {

    private final Map<String, MapeamentoClasseMetodo> urlParaClasseMetodo
            = new HashMap<>();

    private final DateFormat formatadorDate = new SimpleDateFormat("dd/MM/yyyy");
    private final DateTimeFormatter formatadorLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("Inicializando servlet...");

        CarregadorClasses carregadorClasses = new CarregadorClasses();
        try {
            List<Class> classes = carregadorClasses.encontrarClassesNoPacote("br.com.fatecmogidascruzes");

            for (Class classe : classes) {

                Controladora controladora = (Controladora) classe.getDeclaredAnnotation(Controladora.class);
                if (null == controladora) {
                    System.out.println("## Descartada como controladora: " + classe.getName());
                } else {
                    System.out.println("-- Encontrada controladora para o endpoint "
                            + controladora.value() + ": " + classe.getName());

                    Method[] metodos = classe.getDeclaredMethods();
                    for (Method metodo : metodos) {

                        MapeamentoRequisicao mapeamentoRequisicao
                                = metodo.getDeclaredAnnotation(MapeamentoRequisicao.class);

                        if (null == mapeamentoRequisicao) {
                            System.out.println("#### Descartado método da controladora: " + metodo.getName());
                        } else {
                            String endpoint = controladora.value() + mapeamentoRequisicao.value();
                            System.out.println("-- Encontrado método para o endpoint " + endpoint);

                            urlParaClasseMetodo.put(endpoint, new MapeamentoClasseMetodo(classe, metodo));
                        }

                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex.getMessage());
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Tratando requisição...");

        String resultado = "/index.jsp";
        String nomeObjetoResposta = null;
        Object objetoResposta = null;

        String url = request.getRequestURI().substring(
                request.getContextPath().length());

        MapeamentoClasseMetodo mapeamentoClasseMetodo = urlParaClasseMetodo.get(url);
        if (null != mapeamentoClasseMetodo) {
            System.out.println("Quem deve tratar essa requisição é o método "
                    + mapeamentoClasseMetodo.getMetodo().getName() + " da classe " + mapeamentoClasseMetodo.getClasse().getName());

            Class classe = mapeamentoClasseMetodo.getClasse();
            Method metodo = mapeamentoClasseMetodo.getMetodo();

            Parameter[] parametros = metodo.getParameters();
            Object[] argumentos = new Object[parametros.length];
            int i = 0;
            System.out.println("O método tem " + parametros.length + " parâmetros.");
            for (Parameter parametro : parametros) {

                ParametroRequisicao parametroRequisicao = parametro.getDeclaredAnnotation(ParametroRequisicao.class);
                if (null == parametroRequisicao) {
                    argumentos[i] = null;
                    i++;
                    continue;
                }

                System.out.println("Tratando parâmetro " + parametroRequisicao.value() + " com tipo " + parametro.getType() + "...");

                System.out.println("Tentando buscar parâmetro da requisição com nome" + parametroRequisicao.value());
                String valorParametro = request.getParameter(parametroRequisicao.value());
                System.out.println("Valor: " + valorParametro);
                if ("int".equals(parametro.getType().getName())
                        || "java.lang.Integer".equals(parametro.getType().getName())) {
                    argumentos[i] = Integer.valueOf(valorParametro);
                } else if ("double".equals(parametro.getType().getName())
                        || "java.lang.Double".equals(parametro.getType().getName())) {
                    argumentos[i] = Double.valueOf(valorParametro);
                } else if ("java.lang.Date".equals(parametro.getType().getName())) {
                    try {
                        argumentos[i] = formatadorDate.parse(valorParametro);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                } else if ("java.time.LocalDate".equals(parametro.getType().getName())) {
                    argumentos[i] = formatadorLocalDate.parse(valorParametro);
                } else if ("java.lang.String".equals(parametro.getType().getName())) {
                    argumentos[i] = valorParametro;
                } else {
                    try {
                        argumentos[i] = getObjeto(request, parametro.getType(), parametroRequisicao.value());
                    } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
                        ex.printStackTrace();
                    }
                }

                i++;
            }

            try {
                ModeloVisao resultadoMetodo = (ModeloVisao) metodo.invoke(classe.newInstance(), argumentos);
                System.out.println("Vou pegar o resultado");
                resultado = resultadoMetodo.getVisao();
                nomeObjetoResposta = resultadoMetodo.getNomeModelo();
                objetoResposta = resultadoMetodo.getModelo();
            } catch (Exception ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (nomeObjetoResposta != null) {
            request.setAttribute(nomeObjetoResposta, objetoResposta);
        }

        if (!resultado.startsWith("/")) {
            resultado = "/" + resultado;
        }
        request.getRequestDispatcher(resultado).forward(request, response);
    }

    public Object getObjeto(HttpServletRequest request, Class classeDoObjeto, String prefixo) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        Field[] atributos = classeDoObjeto.getDeclaredFields();
        Object objeto = classeDoObjeto.newInstance();
        System.out.println("Tentando preencher objeto da classe " + classeDoObjeto.getName() + "...");
        for (Field atributo : atributos) {
            String nomeParametro = atributo.getName();
            if (null != prefixo) {
                nomeParametro = prefixo + "." + nomeParametro;
            }
            System.out.println("Buscando parâmetro com nome " + nomeParametro + "...");
            String valorParametro = request.getParameter(nomeParametro);
            System.out.println("Valor " + valorParametro);
            if (null != valorParametro) {
                Object valorAtributo = null;
                if ("java.lang.Int".equals(atributo.getType().getName())
                        || "java.lang.Integer".equals(atributo.getType().getName())) {
                    valorAtributo = Integer.valueOf(valorParametro);
                } else if ("java.lang.Double".equals(atributo.getType().getName())) {
                    valorAtributo = Double.valueOf(valorParametro);
                } else if ("java.lang.Float".equals(atributo.getType().getName())) {
                    valorAtributo = Float.valueOf(valorParametro);
                } else if ("java.lang.Short".equals(atributo.getType().getName())) {
                    valorAtributo = Short.valueOf(valorParametro);
                } else if ("java.lang.Long".equals(atributo.getType().getName())) {
                    valorAtributo = Long.valueOf(valorParametro);
                } else if ("java.lang.Date".equals(atributo.getType().getName())) {
                    try {
                        valorAtributo = formatadorDate.parse(valorParametro);
                    } catch (ParseException ex) {
                        Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ("java.time.LocalDate".equals(atributo.getType().getName())) {
                    valorAtributo = formatadorLocalDate.parse(valorParametro);
                } else if ("java.lang.String".equals(atributo.getType().getName())) {
                    valorAtributo = valorParametro;
                } else {
                    valorAtributo = getObjeto(request, atributo.getType(), nomeParametro);
                }
                atributo.setAccessible(true);
                atributo.set(objeto, valorAtributo);
            }
        }
        return objeto;
    }
}
