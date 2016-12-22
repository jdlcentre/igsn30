import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Test {

	public static void main(String [] args){
		String filename = "C:\\VWork\\tan.txt";
		
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.trim().startsWith("edit")){
					System.out.println((sCurrentLine = sCurrentLine.replace("delete", "edit")));
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
