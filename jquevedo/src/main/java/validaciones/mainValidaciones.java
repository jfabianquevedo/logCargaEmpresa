package validaciones;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split;

import pruebas.jquevedo.EntidadBalance;
import pruebas.jquevedo.properties;

public class mainValidaciones {

	public static void main(String[] args) {
		validar v = new validar();
		properties prop = new properties();
		Properties prop1 = new Properties();
		prop1 = prop.leerProperties("validaciones");
		EntidadBalance eb = new EntidadBalance();
		Method[] metodos = eb.getClass().getMethods();
		eb.setNit(new BigDecimal("1234"));
		eb.setAc(new BigDecimal("89964"));
		eb.setPs(new BigDecimal("74136"));
		eb.setAcc(new BigDecimal("74689"));
		eb.setR(new BigDecimal("973261"));
		eb.setAcl(new BigDecimal("1321"));
		eb.setPsc(new BigDecimal("2"));
		eb.setPsl(new BigDecimal("146"));

		BigDecimal valor = getValoresGet(metodos, prop1, eb);
		
		System.out.println(eb.getAc());
		System.out.println(eb.getPs());
		System.out.println(eb.getR());

	}

	public static BigDecimal getValoresGet(Method[] metodos, Properties prop1, EntidadBalance eb) {
		List<Method> listaMetodosGet = new ArrayList<Method>();
		List<Method> listaMetodosSet = new ArrayList<Method>();
		BigDecimal resta = BigDecimal.ZERO;
		for (Method metodo : metodos) {
			if (metodo.getName().startsWith("get")) {
				listaMetodosGet.add(metodo);
			} else if (metodo.getName().startsWith("set")) {
				listaMetodosSet.add(metodo);
			}
		}
		for (int i = 0; i < listaMetodosGet.size(); i++) {
			Method metodoLista = listaMetodosGet.get(i);
			
			int contador = 1;
			String property = (String) prop1.get(metodoLista.getName().toUpperCase().substring(3));

			if (!(property == null)) {
				if (property.contains("+")) {
					try {
						String[] cuentas = property.split("\\+");

						for (Method me : listaMetodosGet) {
							System.out.println(me.getName().substring(3).toUpperCase());
							for (String cuenta : cuentas) {

								if (me.getName().substring(3).equalsIgnoreCase(cuenta)) {
									if (contador == 1) {
										resta = (BigDecimal) me.invoke(eb, null);
										contador++;
									} else {
										resta = resta.subtract((BigDecimal) me.invoke(eb, null));
										

									}
								}
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (property.contains("-")) {

				}
				
				getValoresSet((ArrayList<Method>) listaMetodosSet, eb, resta, metodoLista.getName().toUpperCase().substring(3));
			}
			
		}
		
		return resta;
		

		

	}
	
	public static void getValoresSet(ArrayList<Method> metodosSet,EntidadBalance eb,BigDecimal valor,String cuenta) {
		for(Method metodo : metodosSet) {
		if(metodo.getName().substring(3).toUpperCase().equals(cuenta)) {
			try {
				metodo.invoke(eb,valor);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		}
		
	}

	public static BigDecimal castear(String valor) {
		BigDecimal valorConvertido = BigDecimal.ZERO;
		valorConvertido = new BigDecimal(valor);
		return valorConvertido;

	}

}
