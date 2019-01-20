package pruebas.jquevedo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Properties prop = new Properties();
		properties prop1 = new properties();
		EntidadBalance eb = new EntidadBalance();
		prop = prop1.leerProperties("cuentas");
		Excel excel = new Excel();
		ArrayList<String> a = new ArrayList<String>();
		a = excel.leerExcel(prop);
		
		Method[] metodos = eb.getClass().getMethods();
		List<Method> listaMetodos = new ArrayList<Method>();
		for (Method metodo : metodos) {
			if(metodo.getName().startsWith("set")) {
				listaMetodos.add(metodo);
			}
		}
		for (int i = 0; i < a.size(); i++) {
			String datos = a.get(i);
			String[] separados = datos.split(";;");
			for (int j = 0; j < separados.length; j++) {
				
			}

		}

	}
}
