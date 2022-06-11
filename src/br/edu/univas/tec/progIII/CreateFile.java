package br.edu.univas.tec.progIII;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateFile {
	public CreateFile(String names, String envVarPath, String subjectName) {
		String path = envVarPath + "\\"+fileName(subjectName);
		File newFile = new File(path);
		try {
			FileWriter writtenFile = new FileWriter(path);
			writtenFile.write(names);
			writtenFile.close();
			saveFile(newFile);
			
			System.out.println("\tArquivo salvo com sucesso! :) ");
		} catch (IOException e) {
			System.out.println("Erro ao escrever arquivo .txt.");
		}
	}
	public static void saveFile(File newFile) {
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo .txt.");
		}
	}
	public static String fileName(String subjectName) {
		subjectName = subjectName.toLowerCase().replace(' ', '_')+"_";
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedCurrentDate = currentDate.format(formatter).replace('-', '_');
		return subjectName+formattedCurrentDate+".txt";
	}
}
