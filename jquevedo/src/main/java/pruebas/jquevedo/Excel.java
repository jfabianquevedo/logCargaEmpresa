package pruebas.jquevedo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public ArrayList<String> leerExcel(Properties prop) {
		File file = new File("C:\\Users\\ADMIN\\Documents\\Documents\\Informa\\CargaBalances\\info.xlsx");
		FileInputStream is;
		ArrayList<String> lista = new ArrayList<String>();
		try {
			is = new FileInputStream(file);

			XSSFWorkbook libro = new XSSFWorkbook(is);
			XSSFSheet sheet = libro.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.iterator();
				Cell celda;
				String Entidad ="";
				while (cellIterator.hasNext()) {
					
					celda = cellIterator.next();
					switch (celda.getCellType()) {
					case NUMERIC:
						Entidad= Entidad+";;" + celda.getNumericCellValue();
						break;
					case STRING:
						Entidad=Entidad+";;" + celda.getStringCellValue();
						break;

					default:
						break;
					}
					
				}
				lista.add(Entidad);
				System.out.println(lista.size());

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
