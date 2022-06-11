package br.edu.univas.tec.progIII;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int op = -1;
		String names, fileName = "";
		String envVarPath = System.getenv("CSV_FILE");
		File completePath = new File(envVarPath);
		List<String> fileContent = new ArrayList();
		
		System.out.println("\t\tREGISTRAR CHAMADA");
		do {
			System.out.println("Nome do arquivo a ser lido:\n\tATENÇÃO: Digite 0 para encerrar.");
			fileName = in.nextLine();
			if(fileName.equals("0")) {
				break;
			}else {
				for(File f: completePath.listFiles()) {
					if(f.getName().equalsIgnoreCase(fileName+".csv")) {
						if(f.canRead()) {
							fileContent = readFile(f);
							break;
						}
					}
				}
			}
			if(fileContent.size() == 0) {
				System.out.println("Nenhum arquivo encontrado.");
			}else {
				op = menu(fileContent);
				if(op == 0) {
					break;
				}else {
					System.out.println("Realizar chamada da matéria: "+
					fileContent.get(op - 1)+
					"\n	ATENÇÃO: Digite 9 para encerrar a chamada.");
					
					names = getNames(fileContent.get(op - 1));
					CreateFile cF = new CreateFile(names, envVarPath, fileContent.get(op - 1));
					fileContent.clear();
				}
			}
		}while(true);
		System.out.println("Até!");
		in.close();
	}
	
	public static List<String> readFile(File readable) {
		Scanner readLines = null;
		List<String> fileContent = new ArrayList();
		try {
			readLines = new Scanner(readable);
			while(readLines.hasNextLine()) {
				String host = readLines.nextLine();
				String[] hostA = host.split("\n|;");
				for(String e: hostA) {
					fileContent.add(e);
				}	
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao ler arquivo.");
		}finally {
			readLines.close();
		}
		return fileContent;
		
	}
	public static int menu(List<String> subjects) {
		System.out.println("\t\t:::CHAMADA:::");
		for(int i = 0; i < subjects.size(); i++) {
			System.out.println("\t["+(i + 1)+"] "+subjects.get(i));
		}
		System.out.println("\t[0] Sair");
		int op;
		do {
			System.out.print(">>");
			op = readInt();
			if(op == 0) {
				break;
			}
		}while(op < 0 || op > subjects.size());
		return op;
		
	}
	public static int readInt() {
		Scanner in = new Scanner(System.in);
		int op = -1;
		try {
			op = in.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Digite uma opção do menu, por favor.");
		}
		in.nextLine();
		return op;
	}
	public static String getNames(String subject) {
		Scanner in = new Scanner(System.in);
		String entry;
		String names = "";
		do {
			System.out.print(">>");
			entry = in.nextLine();
			if(entry.equals("9")) {
				break;
			}else {
				names += entry +"\n";
			}
		}while(true);
		return names;
	}
}
