package pruebas.jquevedo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class properties {

	public Properties leerProperties(String name ) {

		try {
			File file = new File(name+".properties");
			Properties prop = new Properties();

			FileInputStream is;
			is = new FileInputStream(file);
			prop.load(is);
			return prop;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void Metodos(EntidadBalance eb) {
		
	}
}
