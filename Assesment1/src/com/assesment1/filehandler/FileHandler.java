package com.assesment1.filehandler;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
	
	public static void main (String args[]) throws IOException {
			System.out.println("Welcome to Lockedme Website!!!!\n"+
					"*********************************************\n"+
											"Developer Name:Sangeetha Sridhar\n"+
											"Language Used: Java 1.8\n"
											);
						System.out.println("Please choose one of the options below:\n"+
								"*********************************************\n"+
											"1.Return the current file names\n"+
											"2.add/delete/search a file");
						Scanner sc = new Scanner(System.in);
						int option = sc.nextInt();
						String path = "E:\\sangeetha\\my files\\doccuments";
						File directoryPath = new File(path);
						String fileName;
						File f;
						int count = 0;
						
						switch(option) {
						
						case 1 :
							File[] fileList = directoryPath.listFiles();
							System.out.println("List of files the given directory");
							for(File file : fileList) {
								System.out.println(file);
							}
							System.exit(0);
						case 2 :
							System.out.println("please choose one of the option\n"+
									"1.Add \n"+
									"2.Delete \n"+
									"3.Search \n"+
									"4.exit");
							option = sc.nextInt();
							switch(option) {
							case  1:
								System.out.println("please enter the name of the file to be added");
								fileName =  sc.next();
								f = new File(directoryPath+"\\"+fileName);
								if(f.exists()) {
									System.out.println("File already exists");
								}
								else {
									System.out.println("No such file exists, creating new file");
									boolean flag = f.createNewFile();
									if(flag) {
										System.out.println("Successfully created new file:"+ f);
									}else {
										System.err.println("Failed to create new file:" + f);
									}

								}
							case 2:
								System.out.println("please enter the name of the file to be deleted");
								fileName =  sc.next();
								f = new File(directoryPath+"\\"+fileName);
								if(!f.exists()) {
									System.out.println("File not exists");
								}
								else {
									boolean flag = f.delete();
									if(flag) {
										System.out.println("Successfully deleted the file:"+ f);
									}else {
										System.err.println("Failed to delete the file:" + f);
									}

								}
							case 3:
								System.out.println("please enter the name of the file to be search");
								fileName =  sc.next();
								
								fileList = directoryPath.listFiles();
								
								for(File file : fileList) {
//									System.out.println(file.getName());
									if(file.getName().equals(fileName)) {
										count=1;
										break;
									}
								}
								if(count == 1) {
									System.out.println(fileName + " found");
								}else {
									System.out.println(fileName + "not found");
								}
								
							case 4:
								System.exit(0);
							}
							
					}
		}
}
		

